package study.todo.service;

import study.todo.domain.category.domain.Category;
import study.todo.domain.category.dto.CreateCategoryRequest;
import study.todo.domain.category.dto.CreateCategoryResponse;
import study.todo.domain.category.dto.UpdateCategoryRequest;
import study.todo.domain.category.dto.UpdateCategoryResponse;
import study.todo.global.common.domain.DelYn;
import study.todo.global.error.exception.BusinessException;
import study.todo.global.error.exception.ErrorCode;
import study.todo.domain.category.dao.CategoryRepository;
import study.todo.domain.category.application.CategoryService;

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