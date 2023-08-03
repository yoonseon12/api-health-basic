package kr.co.wizclass.wizhealth.global.error.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class ErrorResponse {
    private String resultCode;
    private int status;
    private String httpMethod;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> errors;

    public static ErrorResponse of(final ErrorCode error, final ErrorRequestDto requestDto) {
        return new ErrorResponse().builder()
                .resultCode("FAIL")
                .status(error.getStatus())
                .timestamp(LocalDateTime.now())
                .errors(FieldError.of(requestDto))
                .build();
    }

    public static ErrorResponse of(final ErrorCode error, final HttpServletRequest request) {
        return new ErrorResponse().builder()
                .resultCode("FAIL")
                .path(request.getRequestURI())
                .httpMethod(request.getMethod())
                .status(error.getStatus())
                .timestamp(LocalDateTime.now())
                .message(error.getMessage())
                .build();
    }

    public static ErrorResponse of(final ErrorCode error, final BindingResult bindingResult, final HttpServletRequest request) {
        return new ErrorResponse().builder()
                .resultCode("FAIL")
                .path(request.getRequestURI())
                .httpMethod(request.getMethod())
                .status(error.getStatus())
                .timestamp(LocalDateTime.now())
                .message(error.getMessage())
                .errors(FieldError.of(bindingResult))
                .build();
    }
}
