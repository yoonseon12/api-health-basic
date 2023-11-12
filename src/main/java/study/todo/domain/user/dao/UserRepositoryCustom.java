package study.todo.domain.user.dao;

import study.todo.domain.user.dto.UserResponse;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserResponse> findUserWithAuthoritiesByEmail(String email);
    List<UserResponse> findUserProfileById(Long Id);
}
