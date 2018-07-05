package cn.liangjiateng.pojo.VO;

import cn.liangjiateng.pojo.DO.Job;
import cn.liangjiateng.util.DateUtil;
import cn.liangjiateng.util.JsonUtil;


public class JobVO {

    private String jobId;
    private String name;
    private Integer status;
    private Long executedTimes;
    private Integer instanceCount;
    private String createTime;
    private Integer type;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Integer second;
    private String startDate;
    private String endDate;
    private String content;

    public JobVO() {
    }

    public JobVO(Job job) {
        this.jobId = job.getJobId();
        this.name = job.getName();
        this.status = job.getStatus();
        this.executedTimes = job.getExecutedTimes();
        this.instanceCount = job.getInstanceCnt();
        this.createTime = DateUtil.getTime(job.getCreateTime(), DateUtil.DateFormat.PARTIAL);
        this.type = job.getType();
        packCron(job.getCron(), this.type);
        this.content = job.getContent();

    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(Integer instanceCount) {
        this.instanceCount = instanceCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private void packCron(String cron, int type) {
        try {
            this.startDate = JsonUtil.get("start_date", cron);
            this.endDate = JsonUtil.get("end_date", cron);
        } catch (Exception ignored) {

        }

        if (type == Job.Type.INTERVAL.getVal()) {
            try {
                this.year = JsonUtil.getInteger("years", cron);
                this.month = JsonUtil.getInteger("months", cron);
                this.day = JsonUtil.getInteger("days", cron);
                this.hour = JsonUtil.getInteger("hours", cron);
                this.minute = JsonUtil.getInteger("minutes", cron);
                this.second = JsonUtil.getInteger("seconds", cron);
            } catch (Exception ignored) {

            }
        } else if (type == Job.Type.CRON.getVal()) {
            try {
                this.year = JsonUtil.getInteger("year", cron);
                this.month = JsonUtil.getInteger("month", cron);
                this.day = JsonUtil.getInteger("day", cron);
                this.hour = JsonUtil.getInteger("hour", cron);
                this.minute = JsonUtil.getInteger("minute", cron);
                this.second = JsonUtil.getInteger("second", cron);
            } catch (Exception ignored) {

            }
        }
    }
}
