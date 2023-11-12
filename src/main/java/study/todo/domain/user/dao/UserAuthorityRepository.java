package study.todo.domain.user.dao;

import study.todo.domain.user.domain.UserAuthority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
}
