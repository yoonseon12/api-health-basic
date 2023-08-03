package kr.co.wizclass.wizhealth.domain.gym.dto;

import kr.co.wizclass.wizhealth.domain.gym.domain.Gym;
import lombok.Getter;

@Getter
public class UpdateGymRequest {
    private String name;
    private String telNo;

    public static Gym toEntity(UpdateGymRequest updateGymRequest) {
        return Gym.builder()
                .name(updateGymRequest.name)
                .telNo(updateGymRequest.telNo).build();
    }

}