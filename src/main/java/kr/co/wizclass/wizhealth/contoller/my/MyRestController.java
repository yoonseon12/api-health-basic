package kr.co.wizclass.wizhealth.contoller.my;

import kr.co.wizclass.wizhealth.domain.dto.my.FindMyProfileRequest;
import kr.co.wizclass.wizhealth.domain.dto.my.FindMyPropileResponse;
import kr.co.wizclass.wizhealth.service.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/my")
@RequiredArgsConstructor
public class MyRestController {
    private final MyService myService;

    @PostMapping("/profile")
    public ResponseEntity<FindMyPropileResponse> userInfo(@RequestBody final FindMyProfileRequest findMyProfileRequest) {
        return ResponseEntity.ok().body(myService.findMyProfile(findMyProfileRequest));
    }

}
