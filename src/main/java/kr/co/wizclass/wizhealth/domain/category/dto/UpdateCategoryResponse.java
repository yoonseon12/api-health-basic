package kr.co.wizclass.wizhealth.domain.category.dto;

import kr.co.wizclass.wizhealth.domain.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCategoryResponse {
    private Long id;
    private String categoryName;

    public static UpdateCategoryResponse of(Category category) {
        return UpdateCategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }
}
