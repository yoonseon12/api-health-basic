package kr.co.wizclass.wizhealth.domain.dto.gym;

import kr.co.wizclass.wizhealth.domain.entity.Gym;
import lombok.Getter;

import javax.validation.constraints.NotNull;

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