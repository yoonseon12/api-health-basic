package kr.co.wizclass.wizhealth.domain.dto.category;

import kr.co.wizclass.wizhealth.domain.entity.Category;
import kr.co.wizclass.wizhealth.domain.entity.basic.DelYn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryRequest {
    @NotNull
    private String categoryName;

    public static Category toEntity(CreateCategoryRequest createCategoryRequest) {
        return Category.builder()
                .categoryName(createCategoryRequest.categoryName)
                .delYn(DelYn.N)
                .build();
    }
}