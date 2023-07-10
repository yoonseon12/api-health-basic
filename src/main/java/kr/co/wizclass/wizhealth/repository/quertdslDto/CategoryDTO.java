package kr.co.wizclass.wizhealth.repository.quertdslDto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@ToString
public class CategoryDTO {
    private Long id;
    private String name;

    @QueryProjection
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
