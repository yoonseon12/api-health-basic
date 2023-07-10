package kr.co.wizclass.wizhealth.service;

import kr.co.wizclass.wizhealth.domain.dto.exercisePurpose.ExercisePurposeResponse;
import kr.co.wizclass.wizhealth.domain.entity.ExercisePurpose;
import kr.co.wizclass.wizhealth.domain.entity.User;
import kr.co.wizclass.wizhealth.domain.entity.UserExercisePurpose;
import kr.co.wizclass.wizhealth.exception.CustomException;
import kr.co.wizclass.wizhealth.exception.ErrorCode;
import kr.co.wizclass.wizhealth.repository.ExercisePurposeRepository;
import kr.co.wizclass.wizhealth.repository.UserExercisePurposeRepository;
import kr.co.wizclass.wizhealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserExercisePurposeService {
    private final UserRepository userRepository;
    private final UserExercisePurposeRepository userExercisePurposeRepository;
    private final ExercisePurposeRepository exercisePurposeRepository;

    @Transactional
    public List<ExercisePurposeResponse> create(Long userId, List<Long> exercisePurposes) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_EXERCISE_PURPOSE));

        List<ExercisePurpose> findExercisePurposes = exercisePurposes.stream()
                .map(exercisePurposesId -> exercisePurposeRepository.findById(exercisePurposesId)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_EXERCISE_PURPOSE)))
                .collect(Collectors.toList());

        for (ExercisePurpose findExercisePurpose : findExercisePurposes) {
            UserExercisePurpose userExercisePurpose = UserExercisePurpose.builder()
                    .user(findUser)
                    .exercisePurpose(findExercisePurpose)
                    .build();
            userExercisePurposeRepository.save(userExercisePurpose);
        }

        return findExercisePurposes.stream()
                .map(exercisePurpose -> ExercisePurposeResponse.of(exercisePurpose))
                .collect(Collectors.toList());
    }
}
