package kr.co.wizclass.wizhealth.repository.quertdslDto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private String phone;
    private String activated;
    private String authorityName;
    private List<AuthorityDTO> userAuthorities = new ArrayList<>();
    private List<ExercisePurposeDTO> userExercisePurposes = new ArrayList<>();

    // use : UserRepositoryImpl.findUserWithAuthoritiesByEmail
    public UserDTO(String email, String password, String activated, List<AuthorityDTO> userAuthorities) {
        this.email = email;
        this.password = password;
        this.activated = activated;
        this.userAuthorities = userAuthorities;
    }

    // use : UserRepositoryImpl.findUserProfileById
    public UserDTO(Long id, String email, String username, String nickname, String phone, List<ExercisePurposeDTO> userExercisePurposes) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.phone = phone;
        this.userExercisePurposes = userExercisePurposes;
    }

    public UserDTO(String email, String password, String activated, String authorityName) {
        this.email = email;
        this.password = password;
        this.activated = activated;
        this.authorityName = authorityName;
    }
}
