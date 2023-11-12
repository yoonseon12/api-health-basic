package study.todo.domain.gym.dao;

import static study.todo.domain.gym.domain.QGymCategory.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import study.todo.domain.gym.domain.Gym;

import javax.persistence.EntityManager;
import java.util.List;

public class GymCategoryRepositoryImpl implements GymCategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GymCategoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public void deleteGymCategory(Long gymId) {
        queryFactory.delete(gymCategory)
                .where(gymCategory.gym.id.eq(gymId))
                .execute();
    }

    @Override
    public void insertGymCategory(Gym gym, List<Long> categories) {
//        queryFactory
//                .insert(gymCategory)
//                .columns(gymCategory.gym, gymCategory.category)
//                .select(
//                        JPAExpressions
//                                .select(
//                                        Expressions.constant(gym),
//                                        category
//                                )
//                                .from(category)
//                                .where(category.id.in(categories))
//                ).execute();
    }
}
