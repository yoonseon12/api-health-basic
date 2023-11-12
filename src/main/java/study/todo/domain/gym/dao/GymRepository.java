package study.todo.domain.gym.dao;

import study.todo.domain.gym.domain.Gym;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long>, GymRepositoryCustom {
}
