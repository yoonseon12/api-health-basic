package study.todo.domain.category.api;

import study.todo.domain.category.application.CategoryService;
import lombok.RequiredArgsConstructor;
import study.todo.domain.category.dto.CreateCategoryRequest;
import study.todo.domain.category.dto.CreateCategoryResponse;
import study.todo.domain.category.dto.FindCategoryResponse;
import study.todo.domain.category.dto.UpdateCategoryRequest;
import study.todo.domain.category.dto.UpdateCategoryResponse;

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
