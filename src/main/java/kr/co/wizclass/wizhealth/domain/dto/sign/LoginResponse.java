package kr.co.wizclass.wizhealth.domain.dto.sign;

import kr.co.wizclass.wizhealth.common.jwt.dto.TokenDTO;
import kr.co.wizclass.wizhealth.repository.quertdslDto.AuthorityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String email;
    private List<AuthorityDTO> roles;
    private TokenDTO token;

    public static LoginResponse of(String email,
                                      List<AuthorityDTO> roles,
                                      TokenDTO token) {
        return LoginResponse.builder()
                .email(email)
                .roles(roles)
                .token(token)
                .build();
    }
}
