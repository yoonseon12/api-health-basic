package kr.co.wizclass.wizhealth.domain.entity;

import kr.co.wizclass.wizhealth.domain.entity.basic.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "gym_category")
public class GymCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public GymCategory(Gym gym, Category category) {
        this.gym = gym;
        this.category = category;
    }

    public static GymCategory of(Gym gym, Category category) {
        return GymCategory.builder()
                .gym(gym)
                .category(category)
                .build();
    }
}
