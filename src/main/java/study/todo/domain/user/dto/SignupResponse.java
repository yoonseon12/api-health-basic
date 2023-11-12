package study.todo.domain.user.dto;

import study.todo.domain.exercisePurpose.dto.ExercisePurposeResponse;
import study.todo.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupResponse {
    private Long id;
    private String email;
    private String username;
    private String nickname;
    private String phone;
    private List<ExercisePurposeResponse> exercisePurposes;

    public static SignupResponse of(User user, List<ExercisePurposeResponse> exercisePurposes) {
        return SignupResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .exercisePurposes(exercisePurposes)
                .build();
    }
}
