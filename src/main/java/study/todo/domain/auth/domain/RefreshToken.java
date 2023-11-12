package study.todo.domain.auth.domain;

import study.todo.global.common.domain.TokenStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@Table(name = "refresh_token",
        indexes = @Index(name = "idx_email", columnList = "email"))
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "issued_date", nullable = false)
    private LocalDateTime issuedDate;

    @Column(name = "roles", nullable = false)
    private String roles;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_status", nullable = false)
    private TokenStatus tokenStatus;

    @Column(name = "login_date", nullable = false)
    private LocalDateTime loginDate;
}
