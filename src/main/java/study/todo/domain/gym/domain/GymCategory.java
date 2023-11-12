package study.todo.domain.gym.domain;

import study.todo.domain.category.domain.Category;
import study.todo.global.common.domain.BaseEntity;
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
    @JoinColumn(name = "gym_id", columnDefinition = "bigint", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Gym gym;

    @ManyToOne
    @JoinColumn(name = "category_id", columnDefinition = "bigint", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
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
