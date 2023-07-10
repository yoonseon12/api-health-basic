package kr.co.wizclass.wizhealth.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FieldError {
    private String field;
    private Object value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String reason;

    public static FieldError of(String field, String value, String reason) {
        return FieldError.builder()
                .field(field)
                .value(value)
                .reason(reason)
                .build();
    }

    public static FieldError of(org.springframework.validation.FieldError fieldError) {
        return FieldError.of(
                fieldError.getField(),
                (fieldError.getRejectedValue() == null) ? "" : fieldError.getRejectedValue().toString(),
                fieldError.getDefaultMessage());
    }

    public static List<FieldError> of(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(error -> new FieldError(
                        error.getField(),
                        error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    public static List<FieldError> of(ErrorRequestDto requestDto) {
        List<FieldError> fieldErrors = new ArrayList<>();
        Field[] fields = requestDto.getData().getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // private 필드에 접근하기 위해 설정
            Object fieldValue = null;
            try {
                fieldValue = field.get(requestDto.getData());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fieldValue != null) {
                fieldErrors.add(FieldError.builder()
                        .field(field.getName())
                        .value(fieldValue)
                        .build());
            }
        }

        return fieldErrors;
    }
}
