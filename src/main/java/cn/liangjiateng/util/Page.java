package cn.liangjiateng.util;

import java.util.List;

public class Page<T> {

    private int page;

    private int pageSize;   //每页数据量

    private long maxCount;   // 数据总条数

    private List<T> data;

    private int limit;   //保留字段，为了序列化

    private int maxPage; //保留字段，为了序列化

    public Page() {
    }

    public Page(int pageSize) {
        this.pageSize = pageSize;
    }

    public Page(int page, int pageSize, long maxCount, List<T> data) {
        this.page = page;
        this.pageSize = pageSize;
        this.maxCount = maxCount;
        this.data = data;
    }

    public int getPage() {
        return page;
    }


    public int getLimit() {
        return (page - 1) * pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(long maxCount) {
        this.maxCount = maxCount;
    }

    public int getMaxPage() {
        return (int) (maxCount % pageSize > 0 ? (maxCount / pageSize) + 1 : maxCount
                        / pageSize < 1 ? 1 : maxCount / pageSize);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
