package kr.co.wizclass.wizhealth.common.jwt;

public enum TokenStatus {
    OPEN("OPEN"),
    CLOSE("CLOSE");

    private final String status;

    TokenStatus(String status) {
        this.status = status;
    }
    public String getTokenStatus() {
        return status;
    }

    public static boolean isExpiration(TokenStatus tokenStatus) {
        return TokenStatus.CLOSE == tokenStatus;
    }
}
