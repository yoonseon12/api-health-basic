package kr.co.wizclass.wizhealth.domain.user.dao;

import kr.co.wizclass.wizhealth.domain.user.domain.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
}
