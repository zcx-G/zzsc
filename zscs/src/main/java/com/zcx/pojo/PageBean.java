package com.zcx.pojo;

import java.util.List;

/**
 * 分页数据存储
 */
public class PageBean<T> {
    private long count;//
    private List<T> rows;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "count=" + count +
                ", rows=" + rows +
                '}';
    }
}
