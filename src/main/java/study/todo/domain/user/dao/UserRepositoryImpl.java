package study.todo.domain.user.dao;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import study.todo.domain.exercisePurpose.dto.ExercisePurposeResponse;
import study.todo.domain.authority.dto.AuthorityResponse;
import study.todo.domain.user.dto.UserResponse;

import javax.persistence.EntityManager;
import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static study.todo.domain.authority.domain.QAuthority.*;
import static study.todo.domain.exercisePurpose.domain.QExercisePurpose.*;
import static study.todo.domain.user.domain.QUser.*;
import static study.todo.domain.user.domain.QUserAuthority.*;
import static study.todo.domain.user.domain.QUserExercisePurpose.*;

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
