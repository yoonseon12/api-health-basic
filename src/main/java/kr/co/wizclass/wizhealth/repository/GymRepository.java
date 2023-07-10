package kr.co.wizclass.wizhealth.repository;

import kr.co.wizclass.wizhealth.domain.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long>, GymRepositoryCustom {

}
