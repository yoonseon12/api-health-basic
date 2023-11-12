package study.todo.domain.user.dto;

import study.todo.domain.exercisePurpose.dto.ExercisePurposeResponse;
import study.todo.domain.authority.dto.AuthorityResponse;
import study.todo.global.common.domain.AccountStatus;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class UserResponse {
    private Long id;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private String phone;
    private AccountStatus accountStatus;
    private String authorityName;
    private List<AuthorityResponse> userAuthorities = new ArrayList<>();
    private List<ExercisePurposeResponse> userExercisePurposes = new ArrayList<>();

    // use : UserRepositoryImpl.findUserWithAuthoritiesByEmail
    public UserResponse(String email, String password, AccountStatus accountStatus, List<AuthorityResponse> userAuthorities) {
        this.email = email;
        this.password = password;
        this.accountStatus = accountStatus;
        this.userAuthorities = userAuthorities;
    }

    // use : UserRepositoryImpl.findUserProfileById
    public UserResponse(Long id, String email, String username, String nickname, String phone, List<ExercisePurposeResponse> userExercisePurposes) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.phone = phone;
        this.userExercisePurposes = userExercisePurposes;
    }

    public UserResponse(String email, String password, AccountStatus accountStatus, String authorityName) {
        this.email = email;
        this.password = password;
        this.accountStatus = accountStatus;
        this.authorityName = authorityName;
    }
}
