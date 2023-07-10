package kr.co.wizclass.wizhealth.domain.entity;

import kr.co.wizclass.wizhealth.domain.entity.basic.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", columnDefinition = "varchar", length = 30, unique = true)
    private String email;

    @Column(name = "password", columnDefinition = "varchar", length = 255)
    private String password;

    @Column(name = "username", columnDefinition = "varchar", length = 10)
    private String username;

    @Column(name = "nickname", columnDefinition = "varchar", length = 10)
    private String nickname;

    @Column(name = "phone", columnDefinition = "varchar", length = 11)
    private String phone;

    @Column(name = "activated", columnDefinition = "varchar", length = 1)
    private String activated; // Y, N

    @OneToMany(mappedBy = "user")
    private List<UserAuthority> userAuthorities = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserExercisePurpose> userExercisePurposes = new ArrayList<>();

    @Builder
    private User(Long id, String email, String password, String username, String nickname, String phone, String activated) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.phone = phone;
        this.activated = activated;
    }

    public static User of(Long id, String email, String password, String username, String nickname, String phone, String activated) {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .username(username)
                .nickname(nickname)
                .phone(phone)
                .activated(activated)
                .build();
    }

    public static User of(String activated) {
        return User.builder()
                .activated(activated)
                .build();
    }

    public boolean isActivated(User user) {
        if ("N".equals(user.getActivated())) {
            return false;
        }
        return true;
    }
}