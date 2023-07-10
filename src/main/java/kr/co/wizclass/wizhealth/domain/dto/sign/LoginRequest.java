package kr.co.wizclass.wizhealth.domain.dto.sign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotNull(message = "이메일은 필수입력항목 입니다.")
    private String email;
    @NotNull(message = "비밀번호는 필수입력항목 입니다.")
    private String password;
}
