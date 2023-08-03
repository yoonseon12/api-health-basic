package kr.co.wizclass.wizhealth.domain.exercisePurpose.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "exercise_purpose")
public class ExercisePurpose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exercise_purpose_name", columnDefinition = "varchar(255)")//, length = 255)
    private String exercisePurposeName;

}
