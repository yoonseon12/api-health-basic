package study.todo.domain.user.dto;

import study.todo.domain.user.domain.User;
import study.todo.global.common.domain.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequest {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String username;

    @NotNull
    private String nickname;

    private String phone;

    private List<Long> exercisePurposes;


    public static User toEntity(SignupRequest signupRequest) {
        return User.of(
                null
                , signupRequest.email
                , signupRequest.password
                , signupRequest.username
                , signupRequest.nickname
                , signupRequest.phone
                , AccountStatus.ACTIVE);
    }

    public void setEncodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

}

