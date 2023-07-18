package kr.co.wizclass.wizhealth.domain.dto.basic;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class PageInfo {
    private int pageNo;
    private int pageSize;
    private Long totalCount;
    private int totalPages;

    public static PageInfo of(Page page) {
        return PageInfo.builder()
                .pageNo(page.getPageable().getPageNumber())
                .pageSize(page.getPageable().getPageSize())
                .totalCount(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
