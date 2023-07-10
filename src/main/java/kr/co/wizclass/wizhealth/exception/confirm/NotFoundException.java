package kr.co.wizclass.wizhealth.exception.confirm;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final String DEFAULT_ERROR_MESSAGE = "해당 데이터를 찾을 수 없습니다.";

    public NotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
