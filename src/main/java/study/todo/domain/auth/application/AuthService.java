package study.todo.domain.auth.application;

import study.todo.domain.auth.dao.RefreshTokenRepository;
import study.todo.domain.auth.domain.RefreshToken;
import study.todo.global.common.domain.AccountStatus;
import study.todo.global.error.exception.ErrorCode;
import study.todo.domain.auth.dto.LoginRequest;
import study.todo.domain.auth.dto.LoginResponse;
import study.todo.domain.user.domain.User;
import study.todo.global.error.exception.BusinessException;
import study.todo.domain.user.dao.UserRepository;
import study.todo.global.security.TokenProvider;
import study.todo.global.common.domain.TokenStatus;
import study.todo.global.security.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        User findUser = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_ACCOUNT));

        validateLogin(loginRequest, findUser);

        // 인증
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        TokenResponse token = tokenProvider.createToken(authentication);

        return LoginResponse.of(findUser.getEmail(), authentication.getAuthorities(), token);
    }

    private void validateLogin(LoginRequest loginRequest, User user) {
        checkPassword(loginRequest.getPassword(), user.getPassword());
        checkAccountStatus(user.getAccountStatus());
    }

    private void checkPassword(String loginPassword, String encodeUserPassword) {
        if (!passwordEncoder.matches(loginPassword, encodeUserPassword)) {
            throw new BusinessException(ErrorCode.INVALID_ACCOUNT);
        }
    }

    private void checkAccountStatus(AccountStatus accountStatus) {
        if (accountStatus.isInactive()) {
            throw new BusinessException(ErrorCode.DEACTIVATE_USER);
        }
    }

    @Transactional
    public Map<String, String> reIssuanceToken(Map<String, String> refreshTokenMap) {
        String refreshToken = refreshTokenMap.get("refreshToken");
        RefreshToken findRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new BusinessException(ErrorCode.UNAUTHORIZED));

        validateRefreshToken(findRefreshToken);

        return Map.of("accessToken",tokenProvider.createAccessToken(findRefreshToken.getEmail(), findRefreshToken.getRoles()));
    }

    public void validateRefreshToken(RefreshToken refreshToken) {
        checkExpirationDate(refreshToken);
        checkTokenStatus(refreshToken);
    }

    public void checkExpirationDate(RefreshToken refreshToken) {
        if (refreshToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
    }

    public void checkTokenStatus(RefreshToken refreshToken) {
        if (TokenStatus.isExpiration(refreshToken.getTokenStatus())) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
    }
}
