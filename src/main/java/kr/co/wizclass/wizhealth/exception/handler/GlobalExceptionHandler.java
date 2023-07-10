package kr.co.wizclass.wizhealth.exception.handler;

import kr.co.wizclass.wizhealth.exception.CustomException;
import kr.co.wizclass.wizhealth.exception.ErrorCode;
import kr.co.wizclass.wizhealth.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 설정하지 않은 Exception 처리 Handler
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerAllException(Exception e, HttpServletRequest request, WebRequest webRequest) {
        final ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.of(errorCode, request));
    }

    /**
     * HTTP 관련 Exception Handler
     */
    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMessageNotReadableException.class,
    })
    public ResponseEntity<ErrorResponse> handleHttpException(Exception e, HttpServletRequest request) {
        ErrorCode errorCode = null;

        String exceptionName = e.getClass().getSimpleName();
        switch (exceptionName) {
            case "HttpRequestMethodNotSupportedException" :
                errorCode = ErrorCode.METHOD_NOT_ALLOWED;
                break;
            case "HttpMediaTypeNotSupportedException" :
                errorCode = ErrorCode.BAD_REQUEST;
                break;
            case "HttpMessageNotReadableException" :
                errorCode = ErrorCode.METHOD_NOT_READABLE;
                break;
        }

        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.of(errorCode, request));
    }

    /**
     * Custom Exception Handler
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handlerCustomException(CustomException e, HttpServletRequest request) {

        // TODO : 예외 발생 시 request 파라미터 정보를 반환한다. -> 현재 미사용
        // TODO : 사용 시 CustomException 생성자 request에 DTO set 필요
        if (e.getDto() != null) {
            return ResponseEntity.status(e.getErrorCode().getStatus())
                    .body(ErrorResponse.of(e.getErrorCode(), e.getDto()));
        }

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ErrorResponse.of(e.getErrorCode(), request));
    }

    /**
     * Validation 관련 Exception Handler
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBindException(MethodArgumentNotValidException e, HttpServletRequest request) {
        final ErrorCode error = ErrorCode.INVALID_INPUT_VALUE;
        return ResponseEntity.status(error.getStatus())
                .body(ErrorResponse.of(error, e.getBindingResult(), request));
    }

    /**
     * Security 관련 Exception Handler
     */
    @ExceptionHandler({
            BadCredentialsException.class
    })
    public ResponseEntity<ErrorResponse> handleSecurityException(Exception e, HttpServletRequest request) {
        ErrorCode errorCode = null;

        String exceptionName = e.getClass().getSimpleName();
        switch (exceptionName) {
            case "BadCredentialsException" :
                errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
                break;
        }

        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.of(errorCode, request));
    }

}
