package kr.co.wizclass.wizhealth.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static kr.co.wizclass.wizhealth.domain.entity.QGymCategory.gymCategory;

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
}
