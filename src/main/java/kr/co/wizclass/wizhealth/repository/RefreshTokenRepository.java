package kr.co.wizclass.wizhealth.repository;

import kr.co.wizclass.wizhealth.domain.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>, RefreshTokenRepositoryCustom {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
