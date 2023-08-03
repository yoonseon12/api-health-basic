package kr.co.wizclass.wizhealth.domain.gym.dto;

import kr.co.wizclass.wizhealth.domain.gym.domain.Gym;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateGymResponse {
    private String name;
    private String telNo;

    public static UpdateGymResponse of(Gym gym) {
        return UpdateGymResponse.builder()
                .name(gym.getGymName())
                .telNo(gym.getTelNo())
                .build();
    }
}
