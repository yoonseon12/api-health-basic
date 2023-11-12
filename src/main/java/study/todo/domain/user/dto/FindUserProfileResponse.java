package study.todo.domain.user.dto;

import study.todo.domain.exercisePurpose.dto.ExercisePurposeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserProfileResponse {
    private Long userId;
    private String email;
    private String username;
    private String nickname;
    private String phone;
    private List<ExercisePurposeResponse> exercisePurposes;

    public static FindUserProfileResponse of(UserResponse user, List<ExercisePurposeResponse> exercisePurposesList) {
        return FindUserProfileResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .exercisePurposes(exercisePurposesList)
                .build();
    }
}
