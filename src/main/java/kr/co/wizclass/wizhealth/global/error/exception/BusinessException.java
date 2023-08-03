package kr.co.wizclass.wizhealth.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private ErrorRequestDto dto;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
