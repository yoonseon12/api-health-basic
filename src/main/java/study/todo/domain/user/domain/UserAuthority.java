package study.todo.domain.user.domain;

import study.todo.domain.authority.domain.Authority;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_authority")
public class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "bigint", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id", columnDefinition = "bigint", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Authority authority;

    @Builder
    public UserAuthority(User user, Authority authority) {
        this.user = user;
        this.authority = authority;
    }
}
