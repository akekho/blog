package cn.liangjiateng.pojo.DO;

/**
 * Created by Jiateng on 5/28/18.
 */
public class Article extends AbstractDO {

    private Integer id;
    private String title;
    private String content;
    private String contentMd;
    private String preface;
    private Integer status;
    private Integer pv;
    private Integer categoryId;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public enum Status {
        OFFLINE(0),
        ONLINE(1),
        DELETED(2);

        private int val;

        Status(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    public enum SortType {
        TIME_DESC(0),
        TIME_ASC(1),
        PV(2);

        private int val;

        SortType(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

}
