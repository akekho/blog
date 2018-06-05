package cn.liangjiateng.pojo.DO;

/**
 * Created by Jiateng on 5/28/18.
 */
public class Category extends AbstractDO{

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer deleted;

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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
