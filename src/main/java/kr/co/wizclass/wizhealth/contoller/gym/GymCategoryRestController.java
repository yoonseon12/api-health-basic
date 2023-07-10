package kr.co.wizclass.wizhealth.contoller.gym;

import kr.co.wizclass.wizhealth.domain.dto.gymCategory.GymCategoryRequest;
import kr.co.wizclass.wizhealth.domain.dto.gymCategory.GymCategoryResponse;
import kr.co.wizclass.wizhealth.service.GymCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gym-categories")
public class GymCategoryRestController {
    private final GymCategoryService gymCategoryService;

    @PutMapping
    public ResponseEntity<GymCategoryResponse> update(@Valid @RequestBody final GymCategoryRequest gymCategoryRequest) {
        return ResponseEntity.ok(gymCategoryService.update(gymCategoryRequest));
    }

    public void dasda() {

    }

}
