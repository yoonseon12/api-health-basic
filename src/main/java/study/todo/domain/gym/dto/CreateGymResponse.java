package study.todo.domain.gym.dto;

import study.todo.domain.category.dto.CategoryResponse;
import study.todo.domain.gym.domain.Gym;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateGymResponse {
    private Long id;
    private String name;
    private String telNo;
    private List<CategoryResponse> categories = new ArrayList<>();

    public static CreateGymResponse of(Gym gym, List<CategoryResponse> categories) {
        return CreateGymResponse.builder()
                .id(gym.getId())
                .name(gym.getGymName())
                .telNo(gym.getTelNo())
                .categories(categories)
                .build();
    }
}
