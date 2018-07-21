package cn.liangjiateng.pojo.DO;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;

import java.sql.Date;

/**
 * Created by Jiateng on 7/21/18.
 */
public class Log extends AbstractDO {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String pid;
    private String content;
    private Integer level;
    private Integer errCode;
    private Date datetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public enum Level {
        INFO(0),
        WARN(1),
        ERROR(2),
        FATAL(3);

        private int val;

        Level(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

    }

    public static Level getStatusType(int level) throws ServiceException {
        switch (level) {
            case 0:
                return Level.INFO;
            case 1:
                return Level.WARN;
            case 2:
                return Level.ERROR;
            case 3:
                return Level.FATAL;
            default:
                throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "level参数错误");
        }
    }


    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", pid='" + pid + '\'' +
                ", content='" + content + '\'' +
                ", level=" + level +
                ", errCode=" + errCode +
                "} " + super.toString();
    }
}
