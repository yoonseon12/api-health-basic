package kr.co.wizclass.wizhealth.domain.dto.exercisePurpose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExercisePurposeRequest {
    private String exercisePurposeName;
}
