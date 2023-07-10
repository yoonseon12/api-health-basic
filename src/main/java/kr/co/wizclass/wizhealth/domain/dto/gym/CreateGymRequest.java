package kr.co.wizclass.wizhealth.domain.dto.gym;

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
public class CreateGymRequest {
    private Long id;
    private String name;
    private String telNo;
    private List<Long> categories = new ArrayList<>();

    public static Gym toEntity(CreateGymRequest createGymRequest) {
        return Gym.builder()
                .name(createGymRequest.name)
                .telNo(createGymRequest.telNo)
                .build();
    }
}
