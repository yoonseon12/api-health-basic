package kr.co.wizclass.wizhealth.domain.auth.dao;

import kr.co.wizclass.wizhealth.domain.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>, RefreshTokenRepositoryCustom {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
