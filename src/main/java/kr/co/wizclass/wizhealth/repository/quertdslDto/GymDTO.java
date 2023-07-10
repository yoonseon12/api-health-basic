package kr.co.wizclass.wizhealth.repository.quertdslDto;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.wizclass.wizhealth.domain.entity.Gym;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class GymDTO {
    private Long id;
    private String name;
    private String telNo;
    private List<CategoryDTO> categories = new ArrayList<>();

    @QueryProjection
    public GymDTO(Gym gym, List<CategoryDTO> categoryResponseList) {
        this.id = gym.getId();
        this.name = gym.getName();
        categoryResponseList.forEach(category -> {
            this.categories.add(
                    CategoryDTO.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .build());
        });
    }
}
