package kr.co.wizclass.wizhealth.domain.exercisePurpose.dao;

import kr.co.wizclass.wizhealth.domain.exercisePurpose.domain.ExercisePurpose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisePurposeRepository extends JpaRepository<ExercisePurpose, Long> {
}