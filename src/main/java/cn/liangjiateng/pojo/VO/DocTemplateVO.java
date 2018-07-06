package cn.liangjiateng.pojo.VO;

import cn.liangjiateng.pojo.DO.DocTemplate;
import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.util.DateUtil;

public class DocTemplateVO {

    private Integer id;
    private String title;
    private Integer imageId = 0;
    private String thumbUrl;
    private String slimUrl;
    private String originUrl;
    private String content;
    private Integer useTime = 0;
    private Integer status = 0;
    private String createTime;
    private String updateTime;

    public DocTemplateVO() {

    }

    public DocTemplateVO(DocTemplate docTemplate) {
        this.id = docTemplate.getId();
        this.title = docTemplate.getTitle();
        this.imageId = docTemplate.getImageId();
        this.content = docTemplate.getContent();
        this.status = docTemplate.getStatus();
        this.useTime = docTemplate.getUseTime();
        this.createTime = DateUtil.getTime(docTemplate.getCreateTime(), DateUtil.DateFormat.PARTIAL);
        this.updateTime = DateUtil.getTime(docTemplate.getUpdateTime(), DateUtil.DateFormat.PARTIAL);
    }

    public DocTemplateVO(DocTemplate docTemplate, Image image) {
        this.id = docTemplate.getId();
        this.title = docTemplate.getTitle();
        this.imageId = docTemplate.getImageId();
        this.thumbUrl = image.getThumbUrl();
        this.slimUrl = image.getSlimUrl();
        this.originUrl = image.getUrl();
        this.content = docTemplate.getContent();
        this.status = docTemplate.getStatus();
        this.useTime = docTemplate.getUseTime();
        this.createTime = DateUtil.getTime(docTemplate.getCreateTime(), DateUtil.DateFormat.PARTIAL);
        this.updateTime = DateUtil.getTime(docTemplate.getUpdateTime(), DateUtil.DateFormat.PARTIAL);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    @Override
    public String toString() {
        return "DocTemplateVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageId=" + imageId +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", slimUrl='" + slimUrl + '\'' +
                ", originUrl='" + originUrl + '\'' +
                ", content='" + content + '\'' +
                ", useTime=" + useTime +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
