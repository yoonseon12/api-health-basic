package kr.co.wizclass.wizhealth.entity;

import kr.co.wizclass.wizhealth.repository.CategoryRepository;
import kr.co.wizclass.wizhealth.repository.GymCategoryRepository;
import kr.co.wizclass.wizhealth.repository.GymRepository;
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
//        Gym gym = new Gym(null, "A헬스장");
//        gymCategoryRepository.save(new GymCategory(
//                null,
//                gymRepository.save(gym),
//                categoryRepository.save(new Category(null, "헬스"))
//                ));
//        gymCategoryRepository.save(new GymCategory(
//                null,
//                gymRepository.save(gym),
//                categoryRepository.save(new Category(null, "필라테스"))
//        ));
//        gymCategoryRepository.save(new GymCategory(
//                null,
//                gymRepository.save(gym),
//                categoryRepository.save(new Category(null, "골프"))
//        ));
//        Gym gymB = new Gym(null, "B헬스장");
//        gymRepository.save(gymB);
//
//        System.out.println();System.out.println();System.out.println();
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