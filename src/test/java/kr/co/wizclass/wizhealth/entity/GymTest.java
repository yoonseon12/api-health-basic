package kr.co.wizclass.wizhealth.entity;

import kr.co.wizclass.wizhealth.domain.category.dao.CategoryRepository;
import kr.co.wizclass.wizhealth.domain.category.domain.Category;
import kr.co.wizclass.wizhealth.domain.gym.dao.GymCategoryRepository;
import kr.co.wizclass.wizhealth.domain.gym.dao.GymRepository;
import kr.co.wizclass.wizhealth.domain.gym.domain.Gym;
import kr.co.wizclass.wizhealth.domain.gym.domain.GymCategory;
import kr.co.wizclass.wizhealth.global.common.domain.DelYn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional(readOnly = true)
class GymTest {
    @Autowired
    GymRepository gymRepository;
    @Autowired
    GymCategoryRepository gymCategoryRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    @Transactional
    void init() {
        Gym gym = new Gym(null, "A헬스장", "0312223333");
        gymCategoryRepository.save(new GymCategory(
                null,
                gymRepository.save(gym),
                categoryRepository.save(new Category(null, "헬스", DelYn.N))
                ));
        gymCategoryRepository.save(new GymCategory(
                null,
                gymRepository.save(gym),
                categoryRepository.save(new Category(null, "필라테스", DelYn.N))
        ));
        gymCategoryRepository.save(new GymCategory(
                null,
                gymRepository.save(gym),
                categoryRepository.save(new Category(null, "골프", DelYn.N))
        ));
        Gym gymB = new Gym(null, "B헬스장", "0312223333");
        gymRepository.save(gymB);
    }

    @Test
    @DisplayName("다대다 확인")
    void 카테고리_다대다() {
//        Page<GymDTO> results = gymRepository.findGym(PageRequest.of(1,10));
//        for (GymDTO gymResponse : results) {
//            System.out.println(gymResponse);
//        }
    }

}