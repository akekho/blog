package cn.liangjiateng.pojo.DO;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;


/**
 * Created by Jiateng on 5/28/18.
 */
public class Article extends AbstractDO{

    private static final long serialVersionUID = 1L;

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
    public static Status getStatus(int status) throws ServiceException {
        switch (status) {
            case 0:
                return Status.OFFLINE;
            case 1:
                return Status.ONLINE;
            case 2:
                return Status.DELETED;
            default: throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "status参数错误");
        }
    }


    public static SortType getSortType(int type) throws ServiceException {
        switch (type) {
            case 0:
                return SortType.TIME_DESC;
            case 1:
                return SortType.TIME_ASC;
            case 2:
                return SortType.PV;
            default: throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "sortType参数错误");
        }
    }

}
