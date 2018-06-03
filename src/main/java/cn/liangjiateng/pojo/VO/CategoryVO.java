package cn.liangjiateng.pojo.VO;

import cn.liangjiateng.pojo.DO.Category;

public class CategoryVO {

    private Integer id;
    private String name;
    private Long count;

    public CategoryVO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
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
}
