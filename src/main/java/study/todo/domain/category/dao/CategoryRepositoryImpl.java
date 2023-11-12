package study.todo.domain.category.dao;

import static study.todo.domain.category.domain.QCategory.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;

import study.todo.domain.category.domain.Category;
import study.todo.domain.category.domain.QCategory;
import study.todo.global.common.domain.DelYn;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Category> findCategoryById(Long categoryId) {
        Category findCategory = queryFactory
                .selectFrom(category)
                .where(
                        category.id.eq(categoryId),
                        category.delYn.eq(DelYn.N)
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
