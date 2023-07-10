package kr.co.wizclass.wizhealth.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ErrorRequestDto<T> {
    private T data;

    public static <T> ErrorRequestDto of (T data) {
        return ErrorRequestDto.builder()
                .data(data)
                .build();
    }

}
