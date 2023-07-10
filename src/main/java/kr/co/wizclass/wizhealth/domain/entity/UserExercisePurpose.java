package kr.co.wizclass.wizhealth.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_exercise_purpose")
public class UserExercisePurpose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_purpose_id")
    private ExercisePurpose exercisePurpose;

    @Builder
    public UserExercisePurpose(User user, ExercisePurpose exercisePurpose) {
        this.user = user;
        this.exercisePurpose = exercisePurpose;
    }
}
