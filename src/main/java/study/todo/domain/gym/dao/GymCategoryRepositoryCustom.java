package study.todo.domain.gym.dao;

import study.todo.domain.gym.domain.Gym;

import java.util.List;

public interface GymCategoryRepositoryCustom {
    void deleteGymCategory(Long gymId);

    void insertGymCategory(Gym gym, List<Long> categories);
}
