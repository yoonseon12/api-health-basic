package study.todo.domain.auth.dto;

import study.todo.domain.authority.dto.AuthorityResponse;
import study.todo.global.security.dto.TokenResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String email;
    private List<AuthorityResponse> roles;
    private TokenResponse tokenResponse;

    public static LoginResponse of(String email,
                                   Collection<? extends GrantedAuthority> authentication,
                                      TokenResponse tokenResponse) {
        List<AuthorityResponse> roles = authentication.stream()
                .map(role -> new AuthorityResponse(String.valueOf(role)))
                .collect(Collectors.toList());

        return LoginResponse.builder()
                .email(email)
                .roles(roles)
                .tokenResponse(tokenResponse)
                .build();
    }
}
