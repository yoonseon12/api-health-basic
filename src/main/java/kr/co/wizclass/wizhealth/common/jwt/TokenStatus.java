package kr.co.wizclass.wizhealth.common.jwt;

public enum TokenStatus {
    OPEN,
    CLOSE;

    public static boolean isExpiration(TokenStatus tokenStatus) {
        return TokenStatus.CLOSE == tokenStatus;
    }
}
