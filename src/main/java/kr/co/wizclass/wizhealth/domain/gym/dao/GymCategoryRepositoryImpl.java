package kr.co.wizclass.wizhealth.domain.gym.dao;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.wizclass.wizhealth.domain.category.domain.Category;
import kr.co.wizclass.wizhealth.domain.gym.domain.Gym;
import kr.co.wizclass.wizhealth.domain.gym.domain.GymCategory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static kr.co.wizclass.wizhealth.domain.category.domain.QCategory.category;
import static kr.co.wizclass.wizhealth.domain.gym.domain.QGymCategory.gymCategory;

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
