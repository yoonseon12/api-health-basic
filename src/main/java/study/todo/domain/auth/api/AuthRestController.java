package study.todo.domain.auth.api;

import study.todo.domain.auth.dto.LoginRequest;
import study.todo.domain.auth.dto.LoginResponse;
import study.todo.domain.auth.application.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/v1/auths/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody final LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/v1/auths/token")
    public ResponseEntity<Map<String, String>> reIssuanceToken(@RequestBody final Map<String, String> refreshTokenMap) {
        return ResponseEntity.ok().body(authService.reIssuanceToken(refreshTokenMap));
    }

}
