package kr.co.wizclass.wizhealth.repository;

import kr.co.wizclass.wizhealth.domain.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
}
