package kr.co.wizclass.wizhealth.service;

import kr.co.wizclass.wizhealth.domain.dto.my.FindMyProfileRequest;
import kr.co.wizclass.wizhealth.domain.dto.my.FindMyPropileResponse;
import kr.co.wizclass.wizhealth.exception.CustomException;
import kr.co.wizclass.wizhealth.exception.ErrorCode;
import kr.co.wizclass.wizhealth.repository.UserRepository;
import kr.co.wizclass.wizhealth.repository.quertdslDto.ExercisePurposeDTO;
import kr.co.wizclass.wizhealth.repository.quertdslDto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyService {
    private final UserRepository userRepository;

    public FindMyPropileResponse findMyProfile(FindMyProfileRequest findMyProfileRequest) {
        List<UserDTO> findUserProfile = userRepository.findUserProfileByEmail(findMyProfileRequest.getEmail());

        if (findUserProfile == null || findUserProfile.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        UserDTO findUser = findUserProfile.get(0);
        List<ExercisePurposeDTO> findUserExercisePurposes = findUser.getUserExercisePurposes();

        return FindMyPropileResponse.of(findUser, findUserExercisePurposes);

    }
}
