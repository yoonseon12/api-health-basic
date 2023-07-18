package kr.co.wizclass.wizhealth.contoller.gym;

import kr.co.wizclass.wizhealth.common.request.PageableParam;
import kr.co.wizclass.wizhealth.domain.dto.basic.CommonListResponse;
import kr.co.wizclass.wizhealth.domain.dto.gym.*;
import kr.co.wizclass.wizhealth.service.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
//    public ResponseEntity<Page<FindAllGymResponse>> findAll(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
//                                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
//        //Pageable pageable = PageRequest.of(pageNo<1 ? 1 : pageNo, pageSize<1 ? 10 : pageSize);
//        Pageable pageable = PageRequest.of((pageNo-1) * pageNo, pageSize);
//        return ResponseEntity.ok().body(gymService.findGyms(pageable));
//    }

    @GetMapping
    public ResponseEntity<CommonListResponse> findAll(@ModelAttribute PageableParam pageableParam,
                                                      @RequestBody GymSearchCondition gymSearchCondition) {
        Pageable pageable = PageRequest.of(
                pageableParam.getPageNo() - 1, pageableParam.getPageSize());
        return ResponseEntity.ok()
                .body(CommonListResponse.of(gymService.findGyms(pageable, gymSearchCondition)));
    }

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
