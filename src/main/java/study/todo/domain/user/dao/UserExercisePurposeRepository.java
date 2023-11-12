package study.todo.domain.user.dao;

import study.todo.domain.user.domain.UserExercisePurpose;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExercisePurposeRepository extends JpaRepository<UserExercisePurpose, Long> {
}
