package cn.liangjiateng.pojo.VO;

import cn.liangjiateng.pojo.DO.Article;
import cn.liangjiateng.pojo.DO.Category;
import cn.liangjiateng.util.DateUtil;
import cn.liangjiateng.util.HtmlUtil;

public class ArticleVO {

    private Integer id;
    private String title;
    private String content;
    private String desc; //简述
    private String shortDesc; //简短简述
    private String contentMd;
    private String preface;
    private Integer status;
    private Integer pv;
    private String categoryName;
    private String createTime;
    private String updateTime;

    public ArticleVO() {

    }

    public ArticleVO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.contentMd = article.getContentMd();
        this.preface = article.getPreface();
        this.status = article.getStatus();
        this.pv = article.getPv();
        this.desc = computeDesc(article.getContent());
        this.shortDesc = HtmlUtil.delHTMLTag(content).substring(0, Math.min(HtmlUtil.delHTMLTag(content).length(), 75));
        this.createTime = DateUtil.getTime(article.getCreateTime(), DateUtil.DateFormat.PARTIAL);
        this.updateTime = DateUtil.getTime(article.getUpdateTime(), DateUtil.DateFormat.PARTIAL);
    }

    public ArticleVO(Article article, Category category) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.contentMd = article.getContentMd();
        this.preface = article.getPreface();
        this.status = article.getStatus();
        this.pv = article.getPv();
        this.categoryName = category.getName();
        this.desc = computeDesc(article.getContent());
        this.shortDesc = HtmlUtil.delHTMLTag(content).substring(0, Math.min(HtmlUtil.delHTMLTag(content).length(), 75));
        this.createTime = DateUtil.getTime(article.getCreateTime(), DateUtil.DateFormat.PARTIAL);
        this.updateTime = DateUtil.getTime(article.getUpdateTime(), DateUtil.DateFormat.PARTIAL);
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

    public String getPreface() {
        return preface;
    }

    public void setPreface(String preface) {
        this.preface = preface;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentMd() {
        return contentMd;
    }

    public void setContentMd(String contentMd) {
        this.contentMd = contentMd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    /**
     * 计算摘要(desc)算法 //Todo:改进算法
     *
     * @param content 原本内容
     * @return
     */
    private String computeDesc(String content) {
        int index = content.indexOf("<!-- more -->");
        if (index != -1)
            return content.substring(0, index);
        else{
            String pureContent = "<p>" + HtmlUtil.delHTMLTag(this.content) +"</p>";
            return pureContent.substring(0, Math.min(pureContent.length(), 150));
        }
    }
}
