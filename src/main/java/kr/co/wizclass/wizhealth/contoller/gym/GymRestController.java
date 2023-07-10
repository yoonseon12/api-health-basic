package kr.co.wizclass.wizhealth.contoller.gym;

import kr.co.wizclass.wizhealth.domain.dto.gym.CreateGymRequest;
import kr.co.wizclass.wizhealth.domain.dto.gym.CreateGymResponse;
import kr.co.wizclass.wizhealth.domain.dto.gym.UpdateGymRequest;
import kr.co.wizclass.wizhealth.domain.dto.gym.UpdateGymResponse;
import kr.co.wizclass.wizhealth.service.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gyms")
public class GymRestController {
    private final GymService gymService;

//    @GetMapping
//    public ResponseEntity<List<GymResponse>> findAll(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
//                                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo<1 ? 1 : pageNo, pageSize<1 ? 10 : pageSize);
//        return ResponseEntity.ok().body(gymService.findAll(pageable));
//    }

    @PostMapping
    public ResponseEntity<CreateGymResponse> create(@Valid @RequestBody final CreateGymRequest createGymRequest) {
        CreateGymResponse createGymResponse = gymService.create(createGymRequest);
        URI uri = URI.create("/api/gyms/" + createGymResponse.getId());
        return ResponseEntity.created(uri).body(createGymResponse);
    }

    @PutMapping
    public ResponseEntity<UpdateGymResponse> update(@Valid @RequestBody final UpdateGymRequest updateGymRequest) {
        return ResponseEntity.ok().body(gymService.update(updateGymRequest));
    }

}
