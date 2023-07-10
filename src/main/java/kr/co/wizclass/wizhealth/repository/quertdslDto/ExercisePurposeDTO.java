package kr.co.wizclass.wizhealth.repository.quertdslDto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

@Getter
public class ExercisePurposeDTO {
    private Long id;
    private String exercisePurposeName;

    public ExercisePurposeDTO(String exercisePurposeName) {
        this.exercisePurposeName = exercisePurposeName;
    }

    public ExercisePurposeDTO(Long id, String exercisePurposeName) {
        this.id = id;
        this.exercisePurposeName = exercisePurposeName;
    }
}
