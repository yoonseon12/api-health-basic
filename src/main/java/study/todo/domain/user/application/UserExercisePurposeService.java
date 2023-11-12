package study.todo.domain.user.application;

import study.todo.domain.exercisePurpose.dto.ExercisePurposeResponse;
import study.todo.domain.exercisePurpose.domain.ExercisePurpose;
import study.todo.domain.user.domain.User;
import study.todo.domain.user.domain.UserExercisePurpose;
import study.todo.global.error.exception.BusinessException;
import study.todo.global.error.exception.ErrorCode;
import study.todo.domain.exercisePurpose.dao.ExercisePurposeRepository;
import study.todo.domain.user.dao.UserExercisePurposeRepository;
import study.todo.domain.user.dao.UserRepository;
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
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_EXERCISE_PURPOSE));

        List<ExercisePurpose> findExercisePurposes = exercisePurposes.stream()
                .map(exercisePurposesId -> exercisePurposeRepository.findById(exercisePurposesId)
                        .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_EXERCISE_PURPOSE)))
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
