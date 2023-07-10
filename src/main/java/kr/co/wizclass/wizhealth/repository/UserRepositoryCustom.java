package kr.co.wizclass.wizhealth.repository;

import kr.co.wizclass.wizhealth.repository.quertdslDto.UserDTO;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDTO> findUserWithAuthoritiesByEmail(String email);
    List<UserDTO> findUserProfileByEmail(String email);
}
