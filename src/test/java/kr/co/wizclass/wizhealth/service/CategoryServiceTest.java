package kr.co.wizclass.wizhealth.service;

import kr.co.wizclass.wizhealth.domain.category.domain.Category;
import kr.co.wizclass.wizhealth.domain.category.dto.CreateCategoryRequest;
import kr.co.wizclass.wizhealth.domain.category.dto.CreateCategoryResponse;
import kr.co.wizclass.wizhealth.domain.category.dto.UpdateCategoryRequest;
import kr.co.wizclass.wizhealth.domain.category.dto.UpdateCategoryResponse;
import kr.co.wizclass.wizhealth.global.common.domain.DelYn;
import kr.co.wizclass.wizhealth.global.error.exception.BusinessException;
import kr.co.wizclass.wizhealth.global.error.exception.ErrorCode;
import kr.co.wizclass.wizhealth.domain.category.dao.CategoryRepository;
import kr.co.wizclass.wizhealth.domain.category.application.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
@Rollback
class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager em;


    @Test
    @DisplayName("카테고리를 등록한다.")
    void 카테고리_등록() {
        // given
        String newCategoryName = "신규 카테고리명";
        CreateCategoryRequest request = CreateCategoryRequest.builder()
                .categoryName(newCategoryName)
                .build();

        // when
        CreateCategoryResponse response = categoryService.create(request);

        // then
        assertThat(response.getId()).isNotNull();
    }

    @Test
    @DisplayName("카테고리를 수정한다.")
    void 카테고리_수정() {
        // given
        String newCategoryName = "수정할 카테고리명";
        UpdateCategoryRequest request = UpdateCategoryRequest.builder().categoryName(newCategoryName).build();

        // when
        UpdateCategoryResponse response = categoryService.update(1L, request);

        // then
        assertThat(response.getCategoryName()).isEqualTo(newCategoryName);
    }

    @Test
    @DisplayName("카테고리를 삭제한다.")
    @Rollback(false)
    void 카테고리_삭제() {
        // given
        CreateCategoryRequest createRequest = CreateCategoryRequest.builder().categoryName("카테고리").build();
        CreateCategoryResponse createdResponse = categoryService.create(createRequest);
        Long deleteTargetId = createdResponse.getId();

        // when
        categoryService.delete(deleteTargetId);

        em.flush();
        em.clear();

        Category deletedCategory = categoryRepository.findById(deleteTargetId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CATEGORY));

        // then
        //TODO : 해결예정
        assertThat(deletedCategory.getDelYn()).isEqualTo(DelYn.Y);
    }

}