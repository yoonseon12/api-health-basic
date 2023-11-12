package study.todo.domain.gym.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import study.todo.domain.category.dto.CategoryResponse;
import study.todo.domain.gym.dao.GymRepository;
import study.todo.domain.gym.domain.Gym;
import study.todo.domain.gym.dto.CreateGymRequest;
import study.todo.domain.gym.dto.CreateGymResponse;
import study.todo.domain.gym.dto.FindAllGymResponse;
import study.todo.domain.gym.dto.GymCategoryRequest;
import study.todo.domain.gym.dto.GymCategoryResponse;
import study.todo.domain.gym.dto.GymSearchCondition;
import study.todo.domain.gym.dto.UpdateGymRequest;
import study.todo.domain.gym.dto.UpdateGymResponse;
import study.todo.global.error.exception.BusinessException;
import study.todo.global.error.exception.ErrorCode;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GymService {
    private final GymCategoryService gymCategoryService;
    private final GymRepository gymRepository;

    public Page<FindAllGymResponse> findGyms(Pageable pageable, GymSearchCondition gymSearchCondition) {
        return gymRepository.findGymsV4(pageable, gymSearchCondition);
    }

    @Transactional
    public CreateGymResponse create(CreateGymRequest createGymRequest) {
        Gym savedGym = gymRepository.save(CreateGymRequest.toEntity(createGymRequest));
        List<CategoryResponse> savedCategories =
                gymCategoryService.create(savedGym.getId(), createGymRequest.getCategories());

        return CreateGymResponse.of(savedGym, savedCategories);
    }

    @Transactional
    public UpdateGymResponse changeGym(Long gymId, UpdateGymRequest updateGymRequest) {
        Gym persistGym = findByGymId(gymId);
        persistGym.changeGym(UpdateGymRequest.toEntity(updateGymRequest));

        return UpdateGymResponse.of(persistGym);
    }

    private Gym findByGymId(Long gymId) {
        return gymRepository.findById(gymId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_GYM));
    }

    @Transactional
    public GymCategoryResponse updateGymCategory(Long gymId, GymCategoryRequest gymCategoryRequest) {
        Gym persistGym = findByGymId(gymId);
        List<CategoryResponse> savedCategories =
                gymCategoryService.update(persistGym, gymCategoryRequest.getCategories());

        return GymCategoryResponse.of(persistGym, savedCategories);
    }
}
