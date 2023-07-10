package kr.co.wizclass.wizhealth.repository.quertdslDto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthorityDTO {
    private String authorityName;

    public AuthorityDTO(String authorityName) {
        this.authorityName = authorityName;
    }
}
