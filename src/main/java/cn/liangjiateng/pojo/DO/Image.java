package cn.liangjiateng.pojo.DO;

/**
 * Created by Jiateng on 5/28/18.
 */
public class Image extends AbstractDO{

    private Integer id;
    private String name;
    private String url;
    private String thumbUrl;
    private String slimUrl;

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

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }


    public String getSlimUrl() {
        return slimUrl;
    }

    public void setSlimUrl(String slimUrl) {
        this.slimUrl = slimUrl;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", slimUrl='" + slimUrl + '\'' +
                "} " + super.toString();
    }
}
