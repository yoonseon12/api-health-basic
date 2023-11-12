package study.todo.domain.authority.domain;

import study.todo.global.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "authority")
public class Authority extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority_name", columnDefinition = "varchar(50)", unique = true)//, length = 50, unique = true)
    private String authorityName;


}
