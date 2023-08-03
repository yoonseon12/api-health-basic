package kr.co.wizclass.wizhealth.domain.category.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.wizclass.wizhealth.domain.category.domain.Category;
import kr.co.wizclass.wizhealth.domain.category.domain.QCategory;
import kr.co.wizclass.wizhealth.global.common.domain.DelYn;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static kr.co.wizclass.wizhealth.domain.category.domain.QCategory.category;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Category> findCategoryById(Long categoryId) {
        Category findCategory = queryFactory
                .selectFrom(QCategory.category)
                .where(
                        QCategory.category.id.eq(categoryId),
                        QCategory.category.delYn.eq(DelYn.N)
                )
                .fetchOne();

        return Optional.ofNullable(findCategory);
    }

    @Override
    public List<Category> findAllByIdIn(List<Long> categoryIds) {
        return queryFactory.
                selectFrom(category).
                where(category.id.in(categoryIds))
                .fetch();
    }
}
