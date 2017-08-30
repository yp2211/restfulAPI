package com.yp36.common.custom;

public class Pagination {

    private Integer offset;
    private Integer limit;

    public Integer getOffset() {
        if (offset == null)
            offset = 0;
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        if (limit == null || limit == 0)
            limit = 20;
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
