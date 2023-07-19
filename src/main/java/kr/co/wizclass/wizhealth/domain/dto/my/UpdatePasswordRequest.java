package kr.co.wizclass.wizhealth.domain.dto.my;

import kr.co.wizclass.wizhealth.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePasswordRequest {
    @NotNull
    private String currentPassword;
    @NotNull
    private String newPassword;

    private static User toEntity(UpdatePasswordRequest updatePasswordRequest) {
        return User.builder()
                .password(updatePasswordRequest.newPassword)
                .passwordBefore(updatePasswordRequest.currentPassword)
                .build();
    }
}
