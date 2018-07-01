package cn.liangjiateng.pojo.DO;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;

/**
 * 定时任务
 * Created by Jiateng on 6/30/18.
 */
public class Job extends AbstractDO {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String jobId;
    private Integer status;
    private Long executedTimes;
    private String cron;
    private Integer instanceCnt;
    private String content;

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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getExecutedTimes() {
        return executedTimes;
    }

    public void setExecutedTimes(Long executedTimes) {
        this.executedTimes = executedTimes;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getInstanceCnt() {
        return instanceCnt;
    }

    public void setInstanceCnt(Integer instanceCnt) {
        this.instanceCnt = instanceCnt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public enum Status {
        STOPPED(0),
        RUNNING(1),
        PAUSED(2),
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
            case -1:
                return Status.DELETED;
            case 0:
                return Status.STOPPED;
            case 1:
                return Status.RUNNING;
            case 2:
                return Status.PAUSED;
            default:
                throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "status参数错误");
        }
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobId='" + jobId + '\'' +
                ", status=" + status +
                ", executedTimes=" + executedTimes +
                ", cron='" + cron + '\'' +
                ", instanceCnt=" + instanceCnt +
                ", content='" + content + '\'' +
                "} " + super.toString();
    }
}
