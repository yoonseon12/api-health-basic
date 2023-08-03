package kr.co.wizclass.wizhealth.domain.user.dao;

import kr.co.wizclass.wizhealth.domain.user.dto.UserResponse;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserResponse> findUserWithAuthoritiesByEmail(String email);
    List<UserResponse> findUserProfileById(Long Id);
}
