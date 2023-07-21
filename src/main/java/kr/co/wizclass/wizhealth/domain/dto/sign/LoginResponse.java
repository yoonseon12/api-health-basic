package kr.co.wizclass.wizhealth.domain.dto.sign;

import kr.co.wizclass.wizhealth.common.jwt.dto.TokenDTO;
import kr.co.wizclass.wizhealth.repository.quertdslDto.AuthorityDTO;
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
    private List<AuthorityDTO> roles;
    private TokenDTO token;

    public static LoginResponse of(String email,
                                   Collection<? extends GrantedAuthority> authentication,
                                      TokenDTO token) {
        List<AuthorityDTO> roles = authentication.stream()
                .map(role -> new AuthorityDTO(String.valueOf(role)))
                .collect(Collectors.toList());

        return LoginResponse.builder()
                .email(email)
                .roles(roles)
                .token(token)
                .build();
    }
}
