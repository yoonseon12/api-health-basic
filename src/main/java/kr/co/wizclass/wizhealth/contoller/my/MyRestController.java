package kr.co.wizclass.wizhealth.contoller.my;

import kr.co.wizclass.wizhealth.domain.dto.my.FindMyProfileResponse;
import kr.co.wizclass.wizhealth.domain.dto.my.UpdatePasswordRequest;
import kr.co.wizclass.wizhealth.service.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/my")
@RequiredArgsConstructor
public class MyRestController {
    private final MyService myService;

    @PostMapping("/{userId}/profile")
    public ResponseEntity<FindMyProfileResponse> findMyProfile(@PathVariable final Long userId) {
        return ResponseEntity.ok().body(myService.findMyProfile(userId));
    }

    @PutMapping("/{userId}/modified-password")
    public ResponseEntity changeUserOfPassword(@PathVariable final Long userId,
                                               @RequestBody @Valid final UpdatePasswordRequest updatePasswordRequest) {
        myService.changeUserOfPassword(userId, updatePasswordRequest);
        return ResponseEntity.ok().build();
    }

}
