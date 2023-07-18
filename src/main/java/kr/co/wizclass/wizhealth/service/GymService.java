package kr.co.wizclass.wizhealth.service;

import kr.co.wizclass.wizhealth.domain.dto.category.CategoryResponse;
import kr.co.wizclass.wizhealth.domain.dto.gym.*;
import kr.co.wizclass.wizhealth.domain.entity.Gym;
import kr.co.wizclass.wizhealth.exception.CustomException;
import kr.co.wizclass.wizhealth.exception.ErrorCode;
import kr.co.wizclass.wizhealth.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
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
    public UpdateGymResponse update(UpdateGymRequest updateGymRequest) {
        Gym findGym = gymRepository.findById(updateGymRequest.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_GYM));

        findGym.updateGym(UpdateGymRequest.toEntity(updateGymRequest));

        return UpdateGymResponse.of(findGym);

    }
}
