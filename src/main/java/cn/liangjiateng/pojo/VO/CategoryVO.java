package cn.liangjiateng.pojo.VO;

import cn.liangjiateng.pojo.DO.Category;
import cn.liangjiateng.util.DateUtil;

public class CategoryVO {

    private Integer id;
    private String name;
    private Long count;
    private String createTime;
    private String updateTime;

    public CategoryVO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.createTime = DateUtil.getTime(category.getCreateTime(), DateUtil.DateFormat.PARTIAL);
        this.updateTime = DateUtil.getTime(category.getUpdateTime(), DateUtil.DateFormat.PARTIAL);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
