package kr.co.wizclass.wizhealth.domain.category.application;

import kr.co.wizclass.wizhealth.domain.category.dao.CategoryRepository;
import kr.co.wizclass.wizhealth.domain.category.domain.Category;
import kr.co.wizclass.wizhealth.domain.category.dto.*;
import kr.co.wizclass.wizhealth.global.error.exception.BusinessException;
import kr.co.wizclass.wizhealth.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public CreateCategoryResponse create(CreateCategoryRequest createCategoryRequest) {
        Category request = CreateCategoryRequest.toEntity(createCategoryRequest);
        Category savedCategory = categoryRepository.save(request);
        return CreateCategoryResponse.of(savedCategory);
    }

    @Transactional
    public UpdateCategoryResponse update(final Long categoryId, UpdateCategoryRequest updateCategoryRequest) {
        Category pesrsistCategory = findCategoryById(categoryId);
        pesrsistCategory.updateCategory(UpdateCategoryRequest.toEntity(updateCategoryRequest));
        return UpdateCategoryResponse.of(pesrsistCategory);
    }

    @Transactional
    public void delete(final Long categoryId) {
        Category pesrsistCategory = categoryRepository.findCategoryById(categoryId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CATEGORY));
        categoryRepository.delete(pesrsistCategory);
    }

    public FindCategoryResponse find(Long id) {
        Category categoryById = findCategoryById(id);
        return FindCategoryResponse.of(categoryById);
    }

    public List<FindCategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(category -> FindCategoryResponse.of(category))
                .collect(Collectors.toList());
    }

    private Category findCategoryById(final Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CATEGORY));
    }
}
