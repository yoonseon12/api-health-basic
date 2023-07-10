package kr.co.wizclass.wizhealth.domain.dto.category;

import kr.co.wizclass.wizhealth.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCategoryRequest {
    @NotNull
    private String categoryName;

    public static Category toEntity(UpdateCategoryRequest updateCategoryRequest){
        return Category.builder()
                .categoryName(updateCategoryRequest.categoryName)
                .build();
    }


}