package kr.co.wizclass.wizhealth.domain.dto.gymCategory;

import kr.co.wizclass.wizhealth.domain.entity.Category;
import kr.co.wizclass.wizhealth.domain.entity.Gym;
import kr.co.wizclass.wizhealth.domain.entity.GymCategory;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class GymCategoryRequest {
    @NotNull
    private Long gymId;
    private List<Long> categories;

    public static GymCategory toEntity(Gym gym, Category category) {
        return GymCategory.of(gym, category);
    }


}
