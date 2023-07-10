package kr.co.wizclass.wizhealth.contoller.category;

import kr.co.wizclass.wizhealth.domain.dto.category.*;
import kr.co.wizclass.wizhealth.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<FindCategoryResponse>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindCategoryResponse> findAll(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.find(id));
    }

    @PostMapping
    public ResponseEntity<CreateCategoryResponse> create(@RequestBody final CreateCategoryRequest createCategoryRequest) {
        CreateCategoryResponse response = categoryService.create(createCategoryRequest);
        URI uri = URI.create("/api/category"+response.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateCategoryResponse> update(@PathVariable final Long id, @RequestBody final UpdateCategoryRequest updateCategoryRequest) {
        return ResponseEntity.ok(categoryService.update(id, updateCategoryRequest));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable final Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
