package kr.co.wizclass.wizhealth.domain.dto.exercisePurpose;

import kr.co.wizclass.wizhealth.domain.entity.ExercisePurpose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExercisePurposeResponse {
    private Long id;
    private String exercisePurposeName;

    public static ExercisePurposeResponse of(ExercisePurpose exercisePurpose) {
        return ExercisePurposeResponse.builder()
                .id(exercisePurpose.getId())
                .exercisePurposeName(exercisePurpose.getExercisePurposeName())
                .build();
    }
}
