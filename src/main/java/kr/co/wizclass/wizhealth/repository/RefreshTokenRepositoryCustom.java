package kr.co.wizclass.wizhealth.repository;

public interface RefreshTokenRepositoryCustom {
    Boolean existsByEmail(String email);

    Long updateExpirationByEmail(String email);
}
