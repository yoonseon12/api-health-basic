package kr.co.wizclass.wizhealth.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.wizclass.wizhealth.repository.quertdslDto.GymDTO;
import kr.co.wizclass.wizhealth.repository.quertdslDto.QCategoryDTO;
import kr.co.wizclass.wizhealth.repository.quertdslDto.QGymDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static kr.co.wizclass.wizhealth.domain.entity.QCategory.*;
import static kr.co.wizclass.wizhealth.domain.entity.QGym.*;
import static kr.co.wizclass.wizhealth.domain.entity.QGymCategory.*;

public class GymRepositoryImpl implements GymRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GymRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

//    @Override
//    public List<GymResponse> findGym(Pageable pageable) {
//        return queryFactory
//                .selectFrom(gym)
//                .leftJoin(gym.categories, gymCategory)
//                .leftJoin(gymCategory.category, category)
//                .distinct()
//                .transform(groupBy(gym.id).list(
//                        new QGymDTO(gym,
//                                list(new QCategoryDTO(
//                                        category.id,
//                                        category.name)
//                                )
//                        ))
//                );
//
//    }

    @Override
    public Page<GymDTO> findGym(Pageable pageable) {
        List<GymDTO> content = queryFactory
                .selectFrom(gym)
                .leftJoin(gym.categories, gymCategory)
                .leftJoin(gymCategory.category, category)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .transform(groupBy(gym.id).list(
                        new QGymDTO(gym,
                                list(new QCategoryDTO(
                                        category.id,
                                        category.name)
                                )
                        ))
                );
        return new PageImpl<>(content, pageable, content.size());
    }
}
