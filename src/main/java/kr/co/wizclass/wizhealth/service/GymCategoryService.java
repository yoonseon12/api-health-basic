package kr.co.wizclass.wizhealth.service;

import kr.co.wizclass.wizhealth.domain.dto.category.CategoryResponse;
import kr.co.wizclass.wizhealth.domain.dto.gymCategory.GymCategoryRequest;
import kr.co.wizclass.wizhealth.domain.dto.gymCategory.GymCategoryResponse;
import kr.co.wizclass.wizhealth.domain.entity.Category;
import kr.co.wizclass.wizhealth.domain.entity.Gym;
import kr.co.wizclass.wizhealth.exception.CustomException;
import kr.co.wizclass.wizhealth.exception.ErrorCode;
import kr.co.wizclass.wizhealth.repository.CategoryRepository;
import kr.co.wizclass.wizhealth.repository.GymCategoryRepository;
import kr.co.wizclass.wizhealth.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class GymCategoryService {
    private final GymCategoryRepository gymCategoryRepository;
    private final GymRepository gymRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryResponse> create(final Long gymId, final List<Long> categories) {
        Gym findGym = gymRepository.findById(gymId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_GYM));

        List<Category> findCategories = categories.stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY)))
                .collect(Collectors.toList());

        for (Category findCategory : findCategories) {
            gymCategoryRepository.save(GymCategoryRequest.toEntity(findGym, findCategory));
        }

        return findCategories.stream()
                .map(category -> CategoryResponse.of(category))
                .collect(Collectors.toList());
    }

    @Transactional
    public GymCategoryResponse update(GymCategoryRequest gymCategoryRequest) {
        Gym findGym = gymRepository.findById(gymCategoryRequest.getGymId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_GYM));

        List<Category> findCategories = gymCategoryRequest.getCategories().stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY)))
                .collect(Collectors.toList());

        gymCategoryRepository.deleteGymCategory(gymCategoryRequest.getGymId());

        for (Category findCategory : findCategories) {
            gymCategoryRepository.save(GymCategoryRequest.toEntity(findGym, findCategory));
        }

        List<CategoryResponse> categoryResponse = findCategories.stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getCategoryName())
                        .build())
                .collect(Collectors.toList());

        return GymCategoryResponse.of(findGym, categoryResponse);
    }
}
