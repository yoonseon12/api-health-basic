package kr.co.wizclass.wizhealth.contoller.sign;

import kr.co.wizclass.wizhealth.domain.dto.sign.LoginRequest;
import kr.co.wizclass.wizhealth.domain.dto.sign.LoginResponse;
import kr.co.wizclass.wizhealth.domain.dto.sign.SignupRequest;
import kr.co.wizclass.wizhealth.domain.dto.sign.SignupResponse;
import kr.co.wizclass.wizhealth.service.SignService;
import kr.co.wizclass.wizhealth.service.jwt.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignRestController {
    private final SignService signService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody final SignupRequest signupRequest) {
        SignupResponse signupResponse = signService.signup(signupRequest);
        URI uri = URI.create("/signup/"+ signupResponse.getId());
        return ResponseEntity.created(uri).body(signupResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
        LoginResponse loginResponse = signService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody final Map<String, String> refreshTokenMap) {
        return ResponseEntity.ok().body(refreshTokenService.refresh(refreshTokenMap));
    }

}
