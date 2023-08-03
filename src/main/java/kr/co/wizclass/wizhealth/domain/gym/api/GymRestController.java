package kr.co.wizclass.wizhealth.domain.gym.api;

import kr.co.wizclass.wizhealth.domain.gym.application.GymService;
import kr.co.wizclass.wizhealth.domain.gym.dto.*;
import kr.co.wizclass.wizhealth.global.common.request.PageableParam;
import kr.co.wizclass.wizhealth.global.common.response.CommonListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GymRestController {
    private final GymService gymService;

//    @GetMapping
//    public ResponseEntity<Page<FindAllGymResponse>> findAll(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
//                                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
//        //Pageable pageable = PageRequest.of(pageNo<1 ? 1 : pageNo, pageSize<1 ? 10 : pageSize);
//        Pageable pageable = PageRequest.of((pageNo-1) * pageNo, pageSize);
//        return ResponseEntity.ok().body(gymService.findGyms(pageable));
//    }

    @GetMapping("/v1/gyms")
    public ResponseEntity<CommonListResponse> findAll(@ModelAttribute PageableParam pageableParam,
                                                      @RequestBody GymSearchCondition gymSearchCondition) {
        Pageable pageable = PageRequest.of(
                pageableParam.getPageNo() - 1, pageableParam.getPageSize());
        return ResponseEntity.ok()
                .body(CommonListResponse.of(gymService.findGyms(pageable, gymSearchCondition)));
    }

    @PostMapping("/v1/gyms")
    public ResponseEntity<CreateGymResponse> create(@Valid @RequestBody final CreateGymRequest createGymRequest) {
        CreateGymResponse createGymResponse = gymService.create(createGymRequest);
        URI uri = URI.create("/api/gyms/" + createGymResponse.getId());
        return ResponseEntity.created(uri).body(createGymResponse);
    }

    @PutMapping("/v1/gyms")
    public ResponseEntity<UpdateGymResponse> changeGym(@PathVariable Long gymId,
                                                       @Valid @RequestBody final UpdateGymRequest updateGymRequest) {
        return ResponseEntity.ok().body(gymService.changeGym(gymId, updateGymRequest));
    }


    @PutMapping("/v1/gyms/{gymId}/category")
    public ResponseEntity<GymCategoryResponse> updateGymCategory(@PathVariable Long gymId,
                                                                 @Valid @RequestBody final GymCategoryRequest gymCategoryRequest) {
        return ResponseEntity.ok(gymService.updateGymCategory(gymId, gymCategoryRequest));
    }

}
