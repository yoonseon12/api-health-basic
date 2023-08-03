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
public class CreateCategoryResponse {
    private Long id;

    public static CreateCategoryResponse of(Category category) {
        return CreateCategoryResponse.builder()
                .id(category.getId())
                .build();
    }
}
