package kr.co.wizclass.wizhealth.domain.gym.dao;

import kr.co.wizclass.wizhealth.domain.category.domain.Category;
import kr.co.wizclass.wizhealth.domain.gym.domain.Gym;

import java.util.List;

public interface GymCategoryRepositoryCustom {
    void deleteGymCategory(Long gymId);

    void insertGymCategory(Gym gym, List<Long> categories);
}
