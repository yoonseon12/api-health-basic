package study.todo.domain.gym.dto;

import study.todo.domain.category.dto.CategoryResponse;
import study.todo.domain.gym.domain.Gym;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FindAllGymResponse {
    private Long id;
    private String name;
    private String telNo;
    private List<CategoryResponse> categories = new ArrayList<>();

    public static FindAllGymResponse of(Gym gym) {
        List<CategoryResponse> categoryResponseList = gym.getCategories().stream()
                .map(gymCategory ->CategoryResponse.builder()
                        .id(gymCategory.getCategory().getId())
                        .name(gymCategory.getCategory().getCategoryName())
                        .build())
                .collect(Collectors.toList());

        return FindAllGymResponse.builder()
                .id(gym.getId())
                .name(gym.getGymName())
                .telNo(gym.getTelNo())
                .categories(categoryResponseList)
                .build();
    }
}
