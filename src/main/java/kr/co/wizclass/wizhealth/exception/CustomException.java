package kr.co.wizclass.wizhealth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private ErrorRequestDto dto;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
