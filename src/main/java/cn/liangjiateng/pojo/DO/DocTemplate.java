package cn.liangjiateng.pojo.DO;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;

/**
 * Created by Jiateng on 6/16/18.
 */
public class DocTemplate extends AbstractDO {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private Integer imageId = 0;
    private String content;
    private Integer useTime = 0;
    private Integer status = 0;

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
        USE_TIME(2);

        private int val;

        SortType(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

    }

    public static SortType getSortType(int type) throws ServiceException {
        switch (type) {
            case 0:
                return SortType.TIME_DESC;
            case 1:
                return SortType.TIME_ASC;
            case 2:
                return SortType.USE_TIME;
            default: throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "sortType参数错误");
        }
    }

    public static Status getStatusType(int status) throws ServiceException {
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

    @Override
    public String toString() {
        return "DocTemplate{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageId=" + imageId +
                ", content='" + content + '\'' +
                ", useTime=" + useTime +
                ", status=" + status +
                "} " + super.toString();
    }
}
