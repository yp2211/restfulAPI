package com.yp36.common.custom;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * Paging
 * Created by yangpeng on 2016/10/12.
 */
@Getter
@Setter
public class Page<T> {
    protected int pageSize;

    protected int pageNum;//current page number

    protected int count;

    List<T> data = Collections.emptyList();

    public List<T> pageHandle(List<T> list) {
        int fromIndex = (this.pageNum - 1) * this.pageSize;
        int toIndex = this.pageNum * this.pageSize;
        int maxIndex = list.size();
        fromIndex = fromIndex >= 0 ? fromIndex : 0;
        toIndex = toIndex <= maxIndex ? toIndex : maxIndex;
        if (fromIndex > toIndex) {
            fromIndex = 1;
        }
        return list.subList(fromIndex, toIndex);
    }
}
