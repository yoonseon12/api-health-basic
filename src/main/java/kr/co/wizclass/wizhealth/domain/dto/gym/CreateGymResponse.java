package kr.co.wizclass.wizhealth.domain.dto.gym;

import kr.co.wizclass.wizhealth.domain.dto.category.CategoryResponse;
import kr.co.wizclass.wizhealth.domain.entity.Gym;
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
