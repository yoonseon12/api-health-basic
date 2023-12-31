package study.todo.domain.gym.application;

import study.todo.domain.category.dao.CategoryRepository;
import study.todo.domain.category.domain.Category;
import study.todo.domain.category.dto.CategoryResponse;
import study.todo.domain.gym.dao.GymCategoryRepository;
import study.todo.domain.gym.dao.GymRepository;
import study.todo.domain.gym.domain.Gym;
import study.todo.domain.gym.dto.GymCategoryRequest;
import study.todo.global.error.exception.BusinessException;
import study.todo.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GymCategoryService {
    private final GymCategoryRepository gymCategoryRepository;
    private final GymRepository gymRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryResponse> create(final Long gymId, final List<Long> categories) {
        Gym findGym = gymRepository.findById(gymId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_GYM));

        List<Category> findCategories = categories.stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CATEGORY)))
                .collect(Collectors.toList());

        for (Category findCategory : findCategories) {
            gymCategoryRepository.save(GymCategoryRequest.toEntity(findGym, findCategory));
        }

        return findCategories.stream()
                .map(category -> CategoryResponse.of(category))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CategoryResponse> update(final Gym findGym, final List<Long> categories) {

        gymCategoryRepository.deleteGymCategory(findGym.getId());

        gymCategoryRepository.insertByGymCategoryIn(findGym.getId(), categories);

        // TODO : delete -> insert 후 gymCategory 조회해서 반환하도록 수정 필요
        List<Category> findCategories = categoryRepository.findAllByIdIn(categories);

        // TODO : querydsl insert select 수정 필요
//        gymCategoryRepository.insertGymCategory(findGym, categories);

//        for (Category findCategory : findCategories) {
//            gymCategoryRepository.save(GymCategoryRequest.toEntity(findGym, findCategory));
//        }

        return findCategories.stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getCategoryName())
                        .build())
                .collect(Collectors.toList());
    }
}
