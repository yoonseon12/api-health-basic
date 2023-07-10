package kr.co.wizclass.wizhealth.domain.dto.gym;

import kr.co.wizclass.wizhealth.domain.entity.Gym;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UpdateGymRequest {
    @NotNull
    private Long id;
    private String name;
    private String telNo;

    public static Gym toEntity(UpdateGymRequest updateGymRequest) {
        return Gym.of(
                updateGymRequest.id,
                updateGymRequest.name,
                updateGymRequest.telNo);
    }

}