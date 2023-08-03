package kr.co.wizclass.wizhealth.domain.gym.domain;

import kr.co.wizclass.wizhealth.global.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "gym")
public class Gym extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gym_name", columnDefinition = "varchar(255)")//, length = 255)
    private String gymName;

    @Column(name = "telNo", columnDefinition = "varchar(20)")//, length = 20)
    private String telNo;

    @OneToMany(mappedBy = "gym")
    private List<GymCategory> categories = new ArrayList<>();

    @Builder
    public Gym(Long id, String name, String telNo) {
        this.id = id;
        this.gymName = name;
        this.telNo = telNo;
    }

    public void changeGym(Gym gym) {
        this.gymName = gym.gymName;
        this.telNo = gym.telNo;
    }

}
