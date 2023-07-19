package kr.co.wizclass.wizhealth.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.wizclass.wizhealth.domain.dto.category.CategoryResponse;
import kr.co.wizclass.wizhealth.domain.dto.gym.FindAllGymResponse;
import kr.co.wizclass.wizhealth.domain.dto.gym.GymSearchCondition;
import kr.co.wizclass.wizhealth.domain.entity.Gym;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static kr.co.wizclass.wizhealth.domain.entity.QCategory.category;
import static kr.co.wizclass.wizhealth.domain.entity.QGym.gym;
import static kr.co.wizclass.wizhealth.domain.entity.QGymCategory.gymCategory;

public class GymRepositoryImpl implements GymRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GymRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     *  페이징 적용 필요 없는 경우 transform 으로 반환데이터 한번에 조작해서 반환
     */
    @Override
    public Page<FindAllGymResponse> findGymsV1(Pageable pageable) {
        List<FindAllGymResponse> content = queryFactory
                .selectFrom(gym)
                .leftJoin(gym.categories, gymCategory)
                .leftJoin(gymCategory.category, category)
                .orderBy(gym.id.desc())
                .transform(groupBy(gym.id).list(
                        Projections.constructor(FindAllGymResponse.class,
                        gym.id,
                        gym.gymName,
                        gym.telNo,
                        list(Projections.constructor(CategoryResponse.class,
                                category.id,
                                category.categoryName))
                        )
                ));
        return new PageImpl<>(content, pageable, content.size());
    }

    @Override
    public Page<FindAllGymResponse> findGymsV2(Pageable pageable, GymSearchCondition gymSearchCondition) {
        List<Gym> gyms = queryFactory
                .selectFrom(gym)
                .distinct()
                .leftJoin(gym.categories, gymCategory)
                .leftJoin(gymCategory.category, category)
                .where(
                        gymNameEq(gymSearchCondition.getGymName()),
                        categoryIn(gymSearchCondition.getSearchCategories())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(gym.id.asc())
                .fetch();

        Long count = queryFactory
                .select(Wildcard.count)
                .from(gym)
                .leftJoin(gym.categories, gymCategory)
                .where(
                        gymNameEq(gymSearchCondition.getGymName()),
                        categoryIn(gymSearchCondition.getSearchCategories())
                )
                .fetchOne();

        List<FindAllGymResponse> collect = gyms.stream()
                .map(FindAllGymResponse::of)
                .collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, count);
    }

    @Override
    public Page<FindAllGymResponse> findGymsV3(Pageable pageable, GymSearchCondition gymSearchCondition) {
        JPAQuery<Gym> queryGyms = queryFactory
                .selectFrom(gym)
                .distinct()
                .leftJoin(gym.categories, gymCategory)
                .leftJoin(gymCategory.category, category)
                .where(
                        gymNameEq(gymSearchCondition.getGymName()),
                        categoryIn(gymSearchCondition.getSearchCategories())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(gym.id.asc());

        Long totalCount = queryFactory
                .select(gym.countDistinct())
                .from(gym)
                .where(
                        gymNameEq(gymSearchCondition.getGymName())
                ).fetchOne();

        // 카테고리 포함 조건
        if (gymSearchCondition.getSearchCategories() != null && !gymSearchCondition.getSearchCategories().isEmpty()) {
            queryGyms = queryGyms
                    .groupBy(gym.id)
                    .having(category.id.countDistinct().eq(Long.valueOf(gymSearchCondition.getSearchCategories().size())));

            JPAQuery<Long> subQuery = queryFactory
                    .select(gym.id)
                    .from(gym)
                    .leftJoin(gym.categories, gymCategory)
                    .where(
                            gymNameEq(gymSearchCondition.getGymName()),
                            categoryIn(gymSearchCondition.getSearchCategories())
                    )
                    .groupBy(gym.id)
                    .having(gymCategory.category.id.countDistinct().eq(Long.valueOf(gymSearchCondition.getSearchCategories().size())));

            totalCount = queryFactory
                    .select(gym.countDistinct())
                    .from(gym)
                    .where(
                            gym.id.in(subQuery)
                    )
                    .fetchOne();
        }

        List<Gym> resultGyms = queryGyms.fetch();

        List<FindAllGymResponse> collect = resultGyms.stream()
                .map(FindAllGymResponse::of)
                .collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, totalCount);
    }

    @Override
    public Page<FindAllGymResponse> findGymsV4(Pageable pageable, GymSearchCondition gymSearchCondition) {
        JPAQuery<Gym> queryGyms = queryFactory
                .selectFrom(gym)
                .distinct()
                .leftJoin(gym.categories, gymCategory)
                .leftJoin(gymCategory.category, category)
                .where(
                        gymNameEq(gymSearchCondition.getGymName()),
                        categoryIn(gymSearchCondition.getSearchCategories())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(gym.id.asc());

        // 카테고리 포함 조건
        if (gymSearchCondition.getSearchCategories() != null && !gymSearchCondition.getSearchCategories().isEmpty()) {
            queryGyms = queryGyms
                    .groupBy(gym.id)
                    .having(category.id.countDistinct().eq(Long.valueOf(gymSearchCondition.getSearchCategories().size())));
        }

        QueryResults<Gym> gymQueryResults = queryGyms.fetchResults();

        List<Gym> resultGyms = gymQueryResults.getResults();

        List<FindAllGymResponse> collect = resultGyms.stream()
                .map(FindAllGymResponse::of)
                .collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, gymQueryResults.getTotal());
    }

    public BooleanExpression gymNameEq(String gymName) {
        return StringUtils.hasText(gymName) ? gym.gymName.eq(gymName) : null;
    }

    public BooleanExpression categoryIn(List<Long> searchCategories) {
        return searchCategories != null && !searchCategories.isEmpty() ? gymCategory.category.id.in(searchCategories) : null;
    }
}
