package study.todo.domain.gym.api;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.todo.domain.gym.application.GymService;
import study.todo.domain.gym.dto.CreateGymRequest;
import study.todo.domain.gym.dto.CreateGymResponse;
import study.todo.domain.gym.dto.GymCategoryRequest;
import study.todo.domain.gym.dto.GymCategoryResponse;
import study.todo.domain.gym.dto.GymSearchCondition;
import study.todo.domain.gym.dto.UpdateGymRequest;
import study.todo.domain.gym.dto.UpdateGymResponse;
import study.todo.global.common.request.PageableParam;
import study.todo.global.common.response.CommonListResponse;

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
