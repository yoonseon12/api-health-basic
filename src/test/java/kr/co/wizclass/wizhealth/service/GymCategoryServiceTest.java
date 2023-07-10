package kr.co.wizclass.wizhealth.service;

import kr.co.wizclass.wizhealth.repository.CategoryRepository;
import kr.co.wizclass.wizhealth.repository.GymCategoryRepository;
import kr.co.wizclass.wizhealth.repository.GymRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GymCategoryServiceTest {
    @Autowired
    GymRepository gymRepository;
    @Autowired
    GymCategoryRepository gymCategoryRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    GymCategoryService gymCategoryService;

    @BeforeEach
    @Transactional
    void init() {
//        Gym gym = new Gym(null, "A헬스장");
//        gymCategoryRepository.save(new GymCategory(
//                null,
//                gymRepository.save(gym),
//                categoryRepository.save(new Category(null, "헬스"))
//        ));
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
    void createGymCategory() {
//        GymCategoryRequest request = new GymCategoryRequest(2L, 1L);
//
//        GymCategoryResponse response = gymCategoryService.createV1(request);
//
//        assertThat(response).isNotNull();
    }

}