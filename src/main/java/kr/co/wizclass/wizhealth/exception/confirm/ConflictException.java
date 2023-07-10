package kr.co.wizclass.wizhealth.exception.confirm;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    private static final String DEFAULT_ERROR_MESSAGE = "중복된 데이터가 존재합니다.";

    public ConflictException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

    public ConflictException(String message) {
        super(message);
    }
}
