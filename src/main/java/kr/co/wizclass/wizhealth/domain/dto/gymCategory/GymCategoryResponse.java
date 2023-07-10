package kr.co.wizclass.wizhealth.domain.dto.gymCategory;

import kr.co.wizclass.wizhealth.domain.dto.category.CategoryResponse;
import kr.co.wizclass.wizhealth.domain.entity.Gym;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GymCategoryResponse {
    private Long gymId;
    private List<CategoryResponse> categories;

    public static GymCategoryResponse of(Gym gym, List<CategoryResponse> categories) {
        return GymCategoryResponse.builder()
                .gymId(gym.getId())
                .categories(categories)
                .build();
    }

}
