package cn.liangjiateng.pojo.VO;

import cn.liangjiateng.pojo.DO.Article;
import cn.liangjiateng.pojo.DO.Category;

public class ArticleVO {

    private Integer id;
    private String title;
    private String content;
    private String contentMd;
    private String preface;
    private Integer status;
    private Integer pv;
    private String categoryName;

    public ArticleVO() {

    }

    public ArticleVO(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.contentMd = article.getContentMd();
        this.preface = article.getPreface();
        this.status = article.getStatus();
        this.pv = article.getPv();


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
}
