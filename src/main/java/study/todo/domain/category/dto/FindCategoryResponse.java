package study.todo.domain.category.dto;

import study.todo.domain.category.domain.Category;
import study.todo.global.common.domain.DelYn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCategoryResponse {
    private Long id;
    private String categoryName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private DelYn delYn;

    public static FindCategoryResponse of(Category category) {
        return FindCategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .createdDate(category.getCreatedDate())
                .modifiedDate(category.getModifiedDate())
                .delYn(category.getDelYn())
                .build();
    }
}
