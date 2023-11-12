package study.todo.domain.gym.dto;

import study.todo.domain.category.domain.Category;
import study.todo.domain.gym.domain.Gym;
import study.todo.domain.gym.domain.GymCategory;
import lombok.Getter;

import java.util.List;

@Getter
public class GymCategoryRequest {
    private List<Long> categories;
    public static GymCategory toEntity(Gym gym, Category category) {
        return GymCategory.of(gym, category);
    }

}
