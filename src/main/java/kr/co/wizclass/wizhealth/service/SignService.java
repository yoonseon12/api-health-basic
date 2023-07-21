package kr.co.wizclass.wizhealth.service;

import kr.co.wizclass.wizhealth.common.jwt.TokenProvider;
import kr.co.wizclass.wizhealth.common.jwt.dto.TokenDTO;
import kr.co.wizclass.wizhealth.domain.dto.exercisePurpose.ExercisePurposeResponse;
import kr.co.wizclass.wizhealth.domain.dto.sign.LoginRequest;
import kr.co.wizclass.wizhealth.domain.dto.sign.LoginResponse;
import kr.co.wizclass.wizhealth.domain.dto.sign.SignupRequest;
import kr.co.wizclass.wizhealth.domain.dto.sign.SignupResponse;
import kr.co.wizclass.wizhealth.domain.entity.User;
import kr.co.wizclass.wizhealth.domain.entity.UserAuthority;
import kr.co.wizclass.wizhealth.exception.CustomException;
import kr.co.wizclass.wizhealth.exception.ErrorCode;
import kr.co.wizclass.wizhealth.repository.AuthorityRepository;
import kr.co.wizclass.wizhealth.repository.UserAuthorityRepository;
import kr.co.wizclass.wizhealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final UserExercisePurposeService userExercisePurposeService;

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public SignupResponse signup(SignupRequest signupRequest) {
        validateSign(signupRequest);

        signupRequest.setEncodePassword(
                passwordEncoder.encode(signupRequest.getPassword()));

        User user = SignupRequest.toEntity(signupRequest);
        User savedUser = userRepository.save(user);

        // 회원가입시 일반회원 권한 부여
        // TODO : 회원가입 시 일반 권한, test 권한 부여하도록 설계 -> 권한 수정 기능은  Collection로 받도록 설정할 필요 있음.
        UserAuthority roleUser = UserAuthority.builder()
                .authority(authorityRepository.findByAuthorityName("ROLE_USER")
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_AUTHORITY)))
                .user(user)
                .build();
        UserAuthority roleTest = UserAuthority.builder()
                .authority(authorityRepository.findByAuthorityName("ROLE_TEST")
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_AUTHORITY)))
                .user(user)
                .build();
        userAuthorityRepository.save(roleUser);
        userAuthorityRepository.save(roleTest);

        // 운동목적 저장
        List<ExercisePurposeResponse> savedExercisePurposes = new ArrayList<>();
        if (savedExercisePurposes != null && !signupRequest.getExercisePurposes().isEmpty()) {
            savedExercisePurposes =  userExercisePurposeService.create(savedUser.getId(), signupRequest.getExercisePurposes());
        }

        return SignupResponse.of(savedUser, savedExercisePurposes);
    }

    private void validateSign(SignupRequest signupRequest) {
        isEmailDuplicated(signupRequest);
        isNicknameDuplicated(signupRequest);
    }

    public void isEmailDuplicated(SignupRequest signupRequest) {
         if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_SAVED_EMAIL);
         }
    }

    public void isNicknameDuplicated(SignupRequest signupRequest) {
        if (userRepository.existsByNickname(signupRequest.getNickname())) {
            throw new CustomException(ErrorCode.ALREADY_SAVED_NICKNAME);
        }
    }

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        User findUser = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_ACCOUNT));

        validateLogin(loginRequest, findUser);

        // 인증
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        TokenDTO token = tokenProvider.createToken(authentication);

        return LoginResponse.of(findUser.getEmail(), authentication.getAuthorities(), token);
    }

    private void validateLogin(LoginRequest loginRequest, User user) {
        checkPassword(loginRequest.getPassword(), user.getPassword());
        checkActivated(user.getActivated());
    }

    private void checkPassword(String loginPassword, String encodeUserPassword) {
        if (!passwordEncoder.matches(loginPassword, encodeUserPassword)) {
            throw new CustomException(ErrorCode.INVALID_ACCOUNT);
        }
    }

    private void checkActivated(String activatedByUser) {
        if (!"Y".equals(activatedByUser)) {
            throw new CustomException(ErrorCode.DEACTIVATE_USER);
        }
    }
}
