package kr.co.wizclass.wizhealth.domain.gym.dao;

import kr.co.wizclass.wizhealth.domain.gym.domain.GymCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GymCategoryRepository extends JpaRepository<GymCategory, Long>, GymCategoryRepositoryCustom {
    Optional<Long> countByGymIdAndCategoryId(Long gymId, Long categoryId);

    @Modifying
    @Query(value = "INSERT INTO GYM_CATEGORY(GYM_ID, CATEGORY_ID, CREATED_DATE, MODIFIED_DATE) " +
            "SELECT :gymId, ID, NOW(), NOW() FROM CATEGORY WHERE ID IN :categories", nativeQuery = true)
    void insertByGymCategoryIn(@Param("gymId") Long gymId, @Param("categories") List<Long> categories);

}
