package kr.co.wizclass.wizhealth.domain.auth.dao;

public interface RefreshTokenRepositoryCustom {
    Boolean existsByEmail(String email);
    Long updateExpirationByEmail(String email);
}
