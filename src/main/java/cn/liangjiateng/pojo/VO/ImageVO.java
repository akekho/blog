package cn.liangjiateng.pojo.VO;

import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.util.DateUtil;

/**
 * Created by Jiateng on 6/6/18.
 */
public class ImageVO {
    private Integer id;
    private String name;
    private String url;
    private String createTime;
    private String updateTime;

    public ImageVO(Image image) {
        this.id = image.getId();
        this.name = image.getName();
        this.url = image.getUrl();
        this.createTime = DateUtil.getTime(image.getCreateTime(), DateUtil.DateFormat.INTACT);
        this.updateTime = DateUtil.getTime(image.getUpdateTime(), DateUtil.DateFormat.INTACT);
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


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
