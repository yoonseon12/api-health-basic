package study.todo.domain.authority.dao;

import study.todo.domain.authority.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthorityName(String authorityName);
}
