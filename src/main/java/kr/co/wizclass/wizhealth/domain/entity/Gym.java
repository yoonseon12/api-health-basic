package kr.co.wizclass.wizhealth.domain.entity;

import kr.co.wizclass.wizhealth.domain.entity.basic.BaseEntity;
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

    @Column(name = "name", columnDefinition = "varchar", length = 255)
    private String name;

    @Column(name = "telNo", columnDefinition = "varchar", length = 20)
    private String telNo;

    @OneToMany(mappedBy = "gym")
    private List<GymCategory> categories = new ArrayList<>();

    @Builder
    public Gym(Long id, String name, String telNo) {
        this.id = id;
        this.name = name;
        this.telNo = telNo;
    }

    public static Gym of(Long gymId, String name, String telNo) {
        return  Gym.builder()
                .id(gymId)
                .name(name)
                .telNo(telNo)
                .build();
    }

    public void updateGym(Gym gym) {
        this.name = gym.name;
        this.telNo = gym.telNo;
    }

}
