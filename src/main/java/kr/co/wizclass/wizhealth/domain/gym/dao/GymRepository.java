package kr.co.wizclass.wizhealth.domain.gym.dao;

import kr.co.wizclass.wizhealth.domain.gym.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long>, GymRepositoryCustom {
}
