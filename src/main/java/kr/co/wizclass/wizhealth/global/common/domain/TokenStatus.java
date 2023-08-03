package kr.co.wizclass.wizhealth.global.common.domain;

public enum TokenStatus {
    OPEN,
    CLOSE;

    public static boolean isExpiration(TokenStatus tokenStatus) {
        return TokenStatus.CLOSE == tokenStatus;
    }
}
