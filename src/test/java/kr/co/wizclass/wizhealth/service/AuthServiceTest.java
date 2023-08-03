package kr.co.wizclass.wizhealth.service;

import kr.co.wizclass.wizhealth.domain.auth.dto.LoginRequest;
import kr.co.wizclass.wizhealth.domain.auth.dto.LoginResponse;
import kr.co.wizclass.wizhealth.domain.user.application.UserService;
import kr.co.wizclass.wizhealth.domain.user.dto.SignupRequest;
import kr.co.wizclass.wizhealth.domain.user.dto.SignupResponse;
import kr.co.wizclass.wizhealth.global.error.exception.BusinessException;
import kr.co.wizclass.wizhealth.domain.auth.application.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional(readOnly = true)
class AuthServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @Test
    @DisplayName("회원가입을 테스트 한다.")
    @Transactional
    void 회원가입() {
        // given
        SignupRequest request = SignupRequest.builder()
                .email("signup@wizclass.kr")
                .password("1234qwer")
                .username("홍길동")
                .nickname("홍길동")
                .phone("01011112222")
                .build();

        // when
        SignupResponse signupResponse = userService.signup(request);

        // then
        assertThat(signupResponse.getId()).isNotNull();
    }

    @Test
    @DisplayName("이메일 중복체크를 테스트 한다.")
    void 이메일_중복체크() {
        // given
        SignupRequest request = SignupRequest.builder()
                .email("emailDuplicate@wizclass.kr")
                .password("1234qwer")
                .username("홍길동")
                .nickname("홍길동")
                .phone("01011112222")
                .build();
        SignupRequest targetRequest = SignupRequest.builder()
                .email("emailDuplicate@wizclass.kr")
                .password("qwer1234")
                .username("고길동")
                .nickname("고길동")
                .phone("01012345678")
                .build();
        userService.signup(request);

        // when
        BusinessException exception =
                assertThrows(BusinessException.class, () -> userService.isEmailDuplicated(targetRequest));

        // then
        assertThat(exception.getErrorCode().getMessage()).isEqualTo(("사용중인 이메일 입니다."));
    }

    @Test
    @DisplayName("닉네임 중복체크를 테스트 한다.")
    void 닉네임_중복체크() {
        // given
        SignupRequest request = SignupRequest.builder()
                .email("nicknameDuplicate1@wizclass.kr")
                .password("1234qwer")
                .username("홍길동")
                .nickname("길동")
                .phone("01011112222")
                .build();
        SignupRequest targetRequest = SignupRequest.builder()
                .email("nicknameDuplicate2@wizclass.kr")
                .password("qwer1234")
                .username("고길동")
                .nickname("길동")
                .phone("01011112222")
                .build();
        userService.signup(request);

        // when
        BusinessException exception =
                assertThrows(BusinessException.class, () -> userService.isNicknameDuplicated(targetRequest));

        // then
        assertThat(exception.getErrorCode().getMessage()).isEqualTo(("사용중인 닉네임 입니다."));
    }

    @Test
    @DisplayName("로그인 후 토큰 발급을 테스트한다.")
    void 로그인_토큰_발급() {
        // given
        SignupRequest signupRequest = SignupRequest.builder()
                .email("tokenTest@wizclass.kr")
                .password("1234qwer")
                .username("홍길동")
                .nickname("홍길동")
                .phone("01011112222")
                .build();
        SignupResponse signupResponse = userService.signup(signupRequest);

        LoginRequest loginRequest = LoginRequest.builder()
                .email("tokenTest@wizclass.kr")
                .password("1234qwer")
                .build();
        // when
        LoginResponse loginResponse = authService.login(loginRequest);

        // then
        assertThat(loginResponse.getTokenResponse()).isNotNull();
    }
}