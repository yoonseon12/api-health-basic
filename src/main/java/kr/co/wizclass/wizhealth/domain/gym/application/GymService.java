package kr.co.wizclass.wizhealth.domain.gym.application;

import kr.co.wizclass.wizhealth.domain.category.dto.CategoryResponse;
import kr.co.wizclass.wizhealth.domain.gym.dao.GymRepository;
import kr.co.wizclass.wizhealth.domain.gym.domain.Gym;
import kr.co.wizclass.wizhealth.domain.gym.dto.*;
import kr.co.wizclass.wizhealth.global.error.exception.BusinessException;
import kr.co.wizclass.wizhealth.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
