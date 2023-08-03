package kr.co.wizclass.wizhealth.domain.user.application;

import kr.co.wizclass.wizhealth.domain.authority.dao.AuthorityRepository;
import kr.co.wizclass.wizhealth.domain.user.dao.UserAuthorityRepository;
import kr.co.wizclass.wizhealth.domain.user.domain.User;
import kr.co.wizclass.wizhealth.domain.user.domain.UserAuthority;
import kr.co.wizclass.wizhealth.global.error.exception.BusinessException;
import kr.co.wizclass.wizhealth.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthorityService {
    private final UserAuthorityRepository userAuthorityRepository;
    private final AuthorityRepository authorityRepository;

    public void create(User user) {
        // 회원가입시 일반회원 권한 부여
        UserAuthority roleUser = UserAuthority.builder()
                .authority(authorityRepository.findByAuthorityName("ROLE_USER")
                        .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_AUTHORITY)))
                .user(user)
                .build();
        UserAuthority roleTest = UserAuthority.builder()
                .authority(authorityRepository.findByAuthorityName("ROLE_TEST")
                        .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_AUTHORITY)))
                .user(user)
                .build();

        userAuthorityRepository.save(roleUser);
        userAuthorityRepository.save(roleTest);
    }

}
