package kr.co.wizclass.wizhealth.domain.dto.basic;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class CommonListResponse<T> {
    private T data;
    private PageInfo pageInfo;

    public static <T> CommonListResponse of(Page page) {
        return CommonListResponse.builder()
                .data(page.getContent())
                .pageInfo(PageInfo.of(page))
                .build();
    }
}
