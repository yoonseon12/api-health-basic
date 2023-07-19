package kr.co.wizclass.wizhealth.domain.dto.my;

import kr.co.wizclass.wizhealth.domain.dto.exercisePurpose.ExercisePurposeResponse;
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
public class FindMyProfileResponse {
    private Long userId;
    private String email;
    private String username;
    private String nickname;
    private String phone;
    private List<ExercisePurposeResponse> exercisePurposes;

    public static FindMyProfileResponse of(UserDTO user, List<ExercisePurposeResponse> exercisePurposesList) {
        return FindMyProfileResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .exercisePurposes(exercisePurposesList)
                .build();
    }
}
