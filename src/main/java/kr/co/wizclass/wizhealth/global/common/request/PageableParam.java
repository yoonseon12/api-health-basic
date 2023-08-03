package kr.co.wizclass.wizhealth.global.common.request;

public class PageableParam {
    private final Integer DEFAULT_PAGE_NO = 1;
    private final Integer DEFAULT_PAGE_SIZE = 10;

    private Integer pageNo;
    private Integer pageSize;

    public PageableParam(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo < 1 ? DEFAULT_PAGE_NO : pageNo;
        this.pageSize = pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
