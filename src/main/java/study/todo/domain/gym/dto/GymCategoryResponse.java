package study.todo.domain.gym.dto;

import study.todo.domain.category.dto.CategoryResponse;
import study.todo.domain.gym.domain.Gym;
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
