package study.todo.domain.auth.dao;

import static study.todo.domain.auth.domain.QRefreshToken.*;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class RefreshTokenRepositoryImpl implements RefreshTokenRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public RefreshTokenRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return queryFactory
                .select(refreshToken1.id)
                .from(refreshToken1)
                .where(refreshToken1.email.eq(email))
                .limit(1)
                .fetchOne()!= null;
    }

    @Override
    public Long updateExpirationByEmail(String email) {
        return queryFactory
                .update(refreshToken1)
                .set(refreshToken1.expirationDate, LocalDate.of(1900, 1, 1).atStartOfDay())
                .where(refreshToken1.email.eq(email))
                .execute();
    }
}
