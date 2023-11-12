package study.todo.domain.category.dto;

import study.todo.domain.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String name;

    public static CategoryResponse of(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getCategoryName())
                .build();
    }
}
