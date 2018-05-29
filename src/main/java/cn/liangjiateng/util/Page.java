package cn.liangjiateng.util;

import java.util.List;

public class Page<T> {

    private int page;

    private int pageSize;   //每页数据量

    private int maxCount;   // 数据总条数

    private List<T> data;

    public Page(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return page - 1;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getMaxPage() {
        return maxCount % pageSize > 0 ? (maxCount / pageSize) + 1 : maxCount
                / pageSize < 1 ? 1 : maxCount / pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
