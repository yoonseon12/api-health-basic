package kr.co.wizclass.wizhealth.domain.gym.dto;

import kr.co.wizclass.wizhealth.domain.category.domain.Category;
import kr.co.wizclass.wizhealth.domain.gym.domain.Gym;
import kr.co.wizclass.wizhealth.domain.gym.domain.GymCategory;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class GymCategoryRequest {
    private List<Long> categories;
    public static GymCategory toEntity(Gym gym, Category category) {
        return GymCategory.of(gym, category);
    }

}
