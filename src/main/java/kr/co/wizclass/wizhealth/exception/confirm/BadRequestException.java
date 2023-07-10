package kr.co.wizclass.wizhealth.exception.confirm;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "잘못된 요청입니다.";

    public BadRequestException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

    public BadRequestException(String message) {
        super(message);
    }
}
