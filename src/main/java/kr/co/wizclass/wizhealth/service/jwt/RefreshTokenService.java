package kr.co.wizclass.wizhealth.service.jwt;

import kr.co.wizclass.wizhealth.common.jwt.TokenProvider;
import kr.co.wizclass.wizhealth.common.jwt.TokenStatus;
import kr.co.wizclass.wizhealth.domain.entity.RefreshToken;
import kr.co.wizclass.wizhealth.exception.CustomException;
import kr.co.wizclass.wizhealth.exception.ErrorCode;
import kr.co.wizclass.wizhealth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public Map<String, String> refresh(Map<String, String> refreshTokenMap) {
        String refreshToken = refreshTokenMap.get("refreshToken");
        RefreshToken findRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));

        validateRefreshToken(findRefreshToken);

        return Map.of("accessToken",tokenProvider.createAccessToken(findRefreshToken.getEmail(), findRefreshToken.getRoles()));
    }

    public void validateRefreshToken(RefreshToken refreshToken) {
        checkExpirationDate(refreshToken);
        checkTokenStatus(refreshToken);
    }

    public void checkExpirationDate(RefreshToken refreshToken) {
        if (refreshToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
    }

    public void checkTokenStatus(RefreshToken refreshToken) {
        if (TokenStatus.isExpiration(refreshToken.getTokenStatus())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
    }

}
