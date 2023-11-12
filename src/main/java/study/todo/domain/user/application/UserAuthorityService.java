package study.todo.domain.user.application;

import study.todo.domain.authority.dao.AuthorityRepository;
import study.todo.domain.user.dao.UserAuthorityRepository;
import study.todo.domain.user.domain.User;
import study.todo.domain.user.domain.UserAuthority;
import study.todo.global.error.exception.BusinessException;
import study.todo.global.error.exception.ErrorCode;
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
