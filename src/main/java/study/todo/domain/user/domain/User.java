package study.todo.domain.user.domain;

import study.todo.global.common.domain.AccountStatus;
import study.todo.global.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", columnDefinition = "varchar(30)", unique = true)//length = 30, unique = true)
    private String email;

    @Column(name = "password", columnDefinition = "varchar(255)")//, length = 255)
    private String password;

    @Column(name = "password_before", columnDefinition = "varchar(255)")//, length = 255)
    private String passwordBefore;

    @Column(name = "username", columnDefinition = "varchar(10)")//, length = 10)
    private String username;

    @Column(name = "nickname", columnDefinition = "varchar(10)")//, length = 10)
    private String nickname;

    @Column(name = "phone", columnDefinition = "varchar(11)")//, length = 11)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "accountStatus")
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "user")
    private List<UserAuthority> userAuthorities = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserExercisePurpose> userExercisePurposes = new ArrayList<>();

    @Builder
    private User(Long id, String email, String password, String passwordBefore, String username, String nickname, String phone, AccountStatus accountStatus) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.passwordBefore = passwordBefore;
        this.username = username;
        this.nickname = nickname;
        this.phone = phone;
        this.accountStatus = accountStatus;
    }

    public static User of(Long id, String email, String password, String username, String nickname, String phone, AccountStatus accountStatus) {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .username(username)
                .nickname(nickname)
                .phone(phone)
                .accountStatus(accountStatus)
                .build();
    }

    public static User of(AccountStatus accountStatus) {
        return User.builder()
                .accountStatus(accountStatus)
                .build();
    }

    public void changeUserOfPassword(String currentPassword, String newPassword) {
        this.password = newPassword;
        this.passwordBefore = currentPassword;
    }
}
