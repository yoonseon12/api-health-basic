package kr.co.wizclass.wizhealth.domain.authority.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthorityResponse {
    private String authorityName;

    public AuthorityResponse(String authorityName) {
        this.authorityName = authorityName;
    }
}
