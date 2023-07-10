package kr.co.wizclass.wizhealth.domain.dto.my;

import kr.co.wizclass.wizhealth.repository.quertdslDto.ExercisePurposeDTO;
import kr.co.wizclass.wizhealth.repository.quertdslDto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindMyPropileResponse {
    private Long id;
    private String email;
    private String username;
    private String nickname;
    private String phone;
    private List<ExercisePurposeDTO> exercisePurposes;

    public static FindMyPropileResponse of(UserDTO user, List<ExercisePurposeDTO> exercisePurposesList) {
        return FindMyPropileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .exercisePurposes(exercisePurposesList)
                .build();
    }
}
