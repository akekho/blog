package cn.liangjiateng.pojo.DO;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;

/**
 * 一亩三分地信息对象
 * Created by Jiateng on 7/9/18.
 */
public class AcreInfo extends AbstractDO {


    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实用户名
     */
    private String realName;

    /**
     * 金币数
     */
    private Integer point;

    /**
     * 0不运行，1运行，-1删除
     */
    private Integer status = 1;

    /**
     * 签到次数
     */
    private Integer executedTimes = 1;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getExecutedTimes() {
        return executedTimes;
    }

    public void setExecutedTimes(Integer executedTimes) {
        this.executedTimes = executedTimes;
    }

    public enum Status {
        STOPPED(0),
        RUNNING(1),
        DELETED(-1);

        private int val;

        Status(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    public static Status getStatusType(int status) throws ServiceException {
        switch (status) {
            case 1:
                return Status.RUNNING;
            case -1:
                return Status.DELETED;
            case 0:
                return Status.STOPPED;
            default:
                throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "status参数错误");
        }
    }
}
