package kr.co.wizclass.wizhealth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // 400 BAD_REQUEST 잘못된 요청
    BAD_REQUEST(400, "잘못된 요청 입니다."),
    INVALID_INPUT_VALUE(400, "잘못된 입력값 입니다."),
    NOT_READABLE_METHOD(400, "데이터를 읽을 수 없습니다."),

    BAD_REQUEST_SAME_PASSWORD(400, "현재 비밀번호와 변경 비밀번호가 동일할 수 없습니다. "),


    // 401 UNAUTHORIZED 인증되지 않은 접근
    UNAUTHORIZED(401, "인증되지 않은 접근입니다."),

    INVALID_TOKEN(401, "유효하지 않은 JWT 토큰입니다."),
    EXPIRED_TOKEN(401, "만료된 JWT 토큰입니다."),
    UNAUTHORIZED_TOKEN(401, "지원하지 않는 JWT 토큰입니다."),
    WRONG_TOKEN(401, "잘못된 JWT 토큰입니다."),

    INVALID_ACCOUNT(401, "계정정보가 일치하지 않습니다."),
    INVALID_PASSWORD(401, "현재 비밀번호가 일치하지 않습니다."),


    // 403 FORBIDDEN 권한이 없는 접근
    FORBIDDEN(403, "권한이 없는 접근입니다."),
    DEACTIVATE_USER(403, "비활성화 상태 계정입니다."),

    // 404 NOT_FOUND 잘못된 리소스 접근
    NOT_FOUND_OBJECT(404, null),
    NOT_FOUND_CATEGORY(404, "존재하지 않는 카테고리 입니다."),
    NOT_FOUND_GYM(404, "존재하지 않는 운동시설 입니다."),
    NOT_FOUND_AUTHORITY(404, "존재하지 않는 권한 입니다."),
    NOT_FOUND_USER(404, "존재하지 않는 사용자 입니다."),
    NOT_FOUND_EXERCISE_PURPOSE(404, "존재하지 않는 운동목적 입니다."),

    // 405 METHOD_NOT_ALLOWED 허용되지 않은 메서드
    METHOD_NOT_ALLOWED(405, "허용되지 않은 HTTP METHOD 입니다."),

    // 409 CONFLICT 중복된 리소스
    ALREADY_SAVED_GYM_CATEGORY(409, "이미 해당 시설의 동일한 카테고리가 존재합니다."),
    ALREADY_SAVED_NICKNAME(409, "사용중인 닉네임 입니다."),
    ALREADY_SAVED_EMAIL(409, "사용중인 이메일 입니다."),

    // 500 서버 에러
    INTERNAL_SERVER_ERROR(500, "서버 에러 입니다.");

    private final int status;
    private String message;

    // todo : 메서드로 쓸껀지 에러코드를 다 생성할 것 인지 결정 필요.
    public static ErrorCode notFoundObject(String object) {
        NOT_FOUND_OBJECT.message = "존재하지 않는 " + object + " 입니다.";
        return NOT_FOUND_OBJECT;
    }

    public static ErrorCode get(String errorText) {
        for (ErrorCode field : ErrorCode.values()) {
            if (field.name().equals(errorText))
                return field;
        }
        return null;
    }
}
