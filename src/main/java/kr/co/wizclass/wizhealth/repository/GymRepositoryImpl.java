package kr.co.wizclass.wizhealth.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.wizclass.wizhealth.domain.dto.category.CategoryResponse;
import kr.co.wizclass.wizhealth.domain.dto.gym.FindAllGymResponse;
import kr.co.wizclass.wizhealth.domain.entity.Gym;
import kr.co.wizclass.wizhealth.domain.entity.QGym;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
     *  페이징 적용 필요 없는 경우 transform으로 반환데이터 한번에 조작해서 반환
     */
//    @Override
//    public Page<FindAllGymResponse> findGyms(Pageable pageable) {
//        List<FindAllGymResponse> content = queryFactory
//                .selectFrom(gym)
//                .leftJoin(gym.categories, gymCategory)
//                .leftJoin(gymCategory.category, category)
//                .orderBy(gym.id.desc())
//                .transform(groupBy(gym.id).list(
//                        Projections.constructor(FindAllGymResponse.class,
//                        gym.id,
//                        gym.gymName,
//                        gym.telNo,
//                        list(Projections.constructor(CategoryResponse.class,
//                                category.id,
//                                category.categoryName))
//                        )
//                ));
//        return new PageImpl<>(content, pageable, content.size());
//    }

    @Override
    public Page<FindAllGymResponse> findGyms(Pageable pageable) {
        List<Gym> gyms = queryFactory
                .selectFrom(gym)
                .distinct()
                .leftJoin(gym.categories, gymCategory)
                .leftJoin(gymCategory.category, category)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(gym.id.asc())
                .fetch();

        Long count = queryFactory
                .select(Wildcard.count)
                .from(gym)
                .fetchOne();

        List<FindAllGymResponse> collect = gyms.stream()
                .map(FindAllGymResponse::of)
                .collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, count);
    }
}
