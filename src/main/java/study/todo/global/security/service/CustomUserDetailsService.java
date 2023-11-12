package study.todo.global.security.service;

import study.todo.global.error.exception.BusinessException;
import study.todo.global.error.exception.ErrorCode;
import study.todo.domain.user.dao.UserRepository;
import study.todo.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        List<UserResponse> result = userRepository.findUserWithAuthoritiesByEmail(email);

        if (result.get(0).getAccountStatus().isInactive()) {
            throw new BusinessException(ErrorCode.DEACTIVATE_USER);
        };

        List<GrantedAuthority> grantedAuthorities = result.get(0).getUserAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(result.get(0).getEmail(),
                result.get(0).getPassword(),
                grantedAuthorities);
    }
}
