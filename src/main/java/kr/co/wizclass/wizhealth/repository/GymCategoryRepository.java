package kr.co.wizclass.wizhealth.repository;

import kr.co.wizclass.wizhealth.domain.entity.GymCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymCategoryRepository extends JpaRepository<GymCategory, Long>, GymCategoryRepositoryCustom {

    Optional<Long> countByGymIdAndCategoryId(Long gymId, Long categoryId);
}
