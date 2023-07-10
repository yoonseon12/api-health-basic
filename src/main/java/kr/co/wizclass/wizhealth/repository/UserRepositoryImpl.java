package kr.co.wizclass.wizhealth.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.wizclass.wizhealth.repository.quertdslDto.*;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static kr.co.wizclass.wizhealth.domain.entity.QAuthority.authority;
import static kr.co.wizclass.wizhealth.domain.entity.QExercisePurpose.exercisePurpose;
import static kr.co.wizclass.wizhealth.domain.entity.QUser.user;
import static kr.co.wizclass.wizhealth.domain.entity.QUserAuthority.userAuthority;
import static kr.co.wizclass.wizhealth.domain.entity.QUserExercisePurpose.userExercisePurpose;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<UserDTO> findUserWithAuthoritiesByEmail(String email) {
        return queryFactory
                .select(user)
                .from(user)
                .leftJoin(user.userAuthorities, userAuthority)
                .leftJoin(userAuthority.authority, authority)
                .where(user.email.eq(email))
                .transform(groupBy(user.email).list(
                        Projections.constructor(UserDTO.class,
                                user.email,
                                user.password,
                                user.activated,
                                list(Projections.constructor(AuthorityDTO.class,
                                        authority.authorityName)
                                )
                        )
                ));
    }

    @Override
    public List<UserDTO> findUserProfileByEmail(String email) {
        return queryFactory
                .select(user)
                .from(user)
                .leftJoin(user.userExercisePurposes, userExercisePurpose)
                .leftJoin(userExercisePurpose.exercisePurpose, exercisePurpose)
                .where(user.email.eq(email))
                .transform(groupBy(user.id).list(
                        Projections.constructor(UserDTO.class,
                                user.id,
                                user.email,
                                user.username,
                                user.nickname,
                                user.phone,
                                list(Projections.constructor(ExercisePurposeDTO.class,
                                        exercisePurpose.id,
                                        exercisePurpose.exercisePurposeName)
                                )
                        )
                ));
    }
}
