package study.todo.domain.user.application;

import study.todo.domain.user.dto.SignupRequest;
import study.todo.domain.user.dto.SignupResponse;
import study.todo.global.error.exception.BusinessException;
import study.todo.global.error.exception.ErrorCode;
import study.todo.domain.user.dao.UserRepository;
import study.todo.domain.exercisePurpose.dto.ExercisePurposeResponse;
import study.todo.domain.user.dto.FindUserProfileResponse;
import study.todo.domain.user.dto.UpdatePasswordRequest;
import study.todo.domain.user.domain.User;
import study.todo.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserAuthorityService userAuthorityService;
    private final UserExercisePurposeService userExercisePurposeService;

    public FindUserProfileResponse findUserProfile(Long userId) {
        List<UserResponse> findUserProfile = userRepository.findUserProfileById(userId);

        if (findUserProfile == null || findUserProfile.isEmpty())
            throw new BusinessException(ErrorCode.NOT_FOUND_USER);

        UserResponse findUser = findUserProfile.get(0);
        List<ExercisePurposeResponse> findUserExercisePurposes = findUser.getUserExercisePurposes();

        return FindUserProfileResponse.of(findUser, findUserExercisePurposes);
    }

    @Transactional
    public void changeUserOfPassword(Long userId, UpdatePasswordRequest updatePasswordRequest) {
        final User persistUser = findByUserId(userId);
        validatePassword(updatePasswordRequest, persistUser.getPassword());

        persistUser.changeUserOfPassword(persistUser.getPassword(), passwordEncoder.encode(updatePasswordRequest.getCurrentPassword()));
    }

    private void validatePassword(UpdatePasswordRequest updatePasswordRequest, String encodedCurrentPassword) {
        checkEncodedPassword(updatePasswordRequest.getCurrentPassword(), encodedCurrentPassword);
        checkSamePassword(updatePasswordRequest.getNewPassword(), encodedCurrentPassword);
    }

    private void checkEncodedPassword(String inputCurrentPassword, String encodedCurrentPassword) {
        if (!passwordEncoder.matches(inputCurrentPassword, encodedCurrentPassword))
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
    }

    private void checkSamePassword(String inputNewPassword, String encodedCurrentPassword) {
        if (passwordEncoder.matches(inputNewPassword, encodedCurrentPassword))
            throw new BusinessException(ErrorCode.BAD_REQUEST_SAME_PASSWORD);
    }

    @Transactional
    public SignupResponse signup(SignupRequest signupRequest) {
        validateSign(signupRequest);

        signupRequest.setEncodePassword(
                passwordEncoder.encode(signupRequest.getPassword()));

        User user = SignupRequest.toEntity(signupRequest);
        User savedUser = userRepository.save(user);

        // 회원 권한 부여
        userAuthorityService.create(savedUser);

        // 운동목적 저장
        List<ExercisePurposeResponse> savedExercisePurposes = new ArrayList<>();
        if (signupRequest.getExercisePurposes() != null && !signupRequest.getExercisePurposes().isEmpty()) {
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
            throw new BusinessException(ErrorCode.ALREADY_SAVED_EMAIL);
        }
    }

    public void isNicknameDuplicated(SignupRequest signupRequest) {
        if (userRepository.existsByNickname(signupRequest.getNickname())) {
            throw new BusinessException(ErrorCode.ALREADY_SAVED_NICKNAME);
        }
    }

    private User findByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_USER));
    }
}
