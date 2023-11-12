package study.todo.domain.exercisePurpose.dao;

import study.todo.domain.exercisePurpose.domain.ExercisePurpose;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisePurposeRepository extends JpaRepository<ExercisePurpose, Long> {
}