package kr.co.wizclass.wizhealth.domain.category.api;

import kr.co.wizclass.wizhealth.domain.category.application.CategoryService;
import kr.co.wizclass.wizhealth.domain.category.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping("/v1/category")
    public ResponseEntity<List<FindCategoryResponse>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @GetMapping("/v1/category/{id}")
    public ResponseEntity<FindCategoryResponse> findAll(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.find(id));
    }

    @PostMapping("/v1/category")
    public ResponseEntity<CreateCategoryResponse> create(@RequestBody final CreateCategoryRequest createCategoryRequest) {
        CreateCategoryResponse response = categoryService.create(createCategoryRequest);
        URI uri = URI.create("/api/v1/category" + response.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/v1/{id}/category")
    public ResponseEntity<UpdateCategoryResponse> update(@PathVariable final Long id, @RequestBody final UpdateCategoryRequest updateCategoryRequest) {
        return ResponseEntity.ok(categoryService.update(id, updateCategoryRequest));
    }

    @DeleteMapping("/v1/{id}/category")
    public ResponseEntity delete(@PathVariable final Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
