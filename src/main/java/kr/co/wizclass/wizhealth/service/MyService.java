package kr.co.wizclass.wizhealth.service;

import kr.co.wizclass.wizhealth.domain.dto.exercisePurpose.ExercisePurposeResponse;
import kr.co.wizclass.wizhealth.domain.dto.my.FindMyProfileResponse;
import kr.co.wizclass.wizhealth.domain.dto.my.UpdatePasswordRequest;
import kr.co.wizclass.wizhealth.domain.entity.User;
import kr.co.wizclass.wizhealth.exception.CustomException;
import kr.co.wizclass.wizhealth.exception.ErrorCode;
import kr.co.wizclass.wizhealth.repository.UserRepository;
import kr.co.wizclass.wizhealth.repository.quertdslDto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public FindMyProfileResponse findMyProfile(Long userId) {
        List<UserDTO> findUserProfile = userRepository.findUserProfileById(userId);

        if (findUserProfile == null || findUserProfile.isEmpty())
            throw new CustomException(ErrorCode.NOT_FOUND_USER);

        UserDTO findUser = findUserProfile.get(0);
        List<ExercisePurposeResponse> findUserExercisePurposes = findUser.getUserExercisePurposes();

        return FindMyProfileResponse.of(findUser, findUserExercisePurposes);
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
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }

    private void checkSamePassword(String inputNewPassword, String encodedCurrentPassword) {
        if (passwordEncoder.matches(inputNewPassword, encodedCurrentPassword))
            throw new CustomException(ErrorCode.BAD_REQUEST_SAME_PASSWORD);
    }

    private User findByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }
}
