package kr.co.wizclass.wizhealth.domain.dto.gym;

import kr.co.wizclass.wizhealth.domain.entity.Gym;
import kr.co.wizclass.wizhealth.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateGymRequest {
    @NotNull
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
