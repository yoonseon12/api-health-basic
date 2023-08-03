package kr.co.wizclass.wizhealth.domain.user.api;

import kr.co.wizclass.wizhealth.domain.auth.application.AuthService;
import kr.co.wizclass.wizhealth.domain.user.dto.SignupRequest;
import kr.co.wizclass.wizhealth.domain.user.dto.SignupResponse;
import kr.co.wizclass.wizhealth.domain.user.dto.FindUserProfileResponse;
import kr.co.wizclass.wizhealth.domain.user.dto.UpdatePasswordRequest;
import kr.co.wizclass.wizhealth.domain.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping("/v1/users/{userId}/profile")
    public ResponseEntity<FindUserProfileResponse> findMyProfile(@PathVariable final Long userId) {
        return ResponseEntity.ok().body(userService.findUserProfile(userId));
    }

    @PutMapping("/v1/users/{userId}/password")
    public ResponseEntity changeUserOfPassword(@PathVariable final Long userId,
                                               @RequestBody @Valid final UpdatePasswordRequest updatePasswordRequest) {
        userService.changeUserOfPassword(userId, updatePasswordRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/users/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody final SignupRequest signupRequest) {
        SignupResponse signupResponse = userService.signup(signupRequest);
        URI uri = URI.create("/v1/users/signup/" + signupResponse.getId());
        return ResponseEntity.created(uri).body(signupResponse);
    }
}
