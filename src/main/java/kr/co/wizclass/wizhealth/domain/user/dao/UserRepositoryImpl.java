package kr.co.wizclass.wizhealth.domain.user.dao;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.wizclass.wizhealth.domain.exercisePurpose.dto.ExercisePurposeResponse;
import kr.co.wizclass.wizhealth.domain.authority.dto.AuthorityResponse;
import kr.co.wizclass.wizhealth.domain.user.dto.UserResponse;

import javax.persistence.EntityManager;
import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static kr.co.wizclass.wizhealth.domain.authority.domain.QAuthority.authority;
import static kr.co.wizclass.wizhealth.domain.exercisePurpose.domain.QExercisePurpose.exercisePurpose;
import static kr.co.wizclass.wizhealth.domain.user.domain.QUser.user;
import static kr.co.wizclass.wizhealth.domain.user.domain.QUserAuthority.userAuthority;
import static kr.co.wizclass.wizhealth.domain.user.domain.QUserExercisePurpose.userExercisePurpose;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<UserResponse> findUserWithAuthoritiesByEmail(String email) {
        return queryFactory
                .select(user)
                .from(user)
                .leftJoin(user.userAuthorities, userAuthority)
                .leftJoin(userAuthority.authority, authority)
                .where(user.email.eq(email))
                .transform(groupBy(user.email).list(
                        Projections.constructor(UserResponse.class,
                                user.email,
                                user.password,
                                user.accountStatus,
                                list(Projections.constructor(AuthorityResponse.class,
                                        authority.authorityName)
                                )
                        )
                ));
    }

    @Override
    public List<UserResponse> findUserProfileById(Long id) {
        return queryFactory
                .select(user)
                .from(user)
                .leftJoin(user.userExercisePurposes, userExercisePurpose)
                .leftJoin(userExercisePurpose.exercisePurpose, exercisePurpose)
                .where(user.id.eq(id))
                .transform(groupBy(user.id).list(
                        Projections.constructor(UserResponse.class,
                                user.id,
                                user.email,
                                user.username,
                                user.nickname,
                                user.phone,
                                list(Projections.constructor(ExercisePurposeResponse.class,
                                        exercisePurpose.id,
                                        exercisePurpose.exercisePurposeName)
                                )
                        )
                ));
    }
}
