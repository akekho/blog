package cn.liangjiateng.pojo.DO;


import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;

/**
 * leetcode_info
 * @author 
 */
public class LeetcodeInfo extends AbstractDO {


    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户别名(用于url)
     */
    private String userSlag;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 地址
     */
    private String location;

    /**
     * 学校
     */
    private String school;

    /**
     * 完场比赛数
     */
    private Integer finishedContests;

    /**
     * 排名
     */
    private Integer rating;

    /**
     * 总排名
     */
    private String globalRank;

    /**
     * 解决问题数
     */
    private String solvedQuestion;

    /**
     * 解决问题数
     */
    private String acceptedSubmission;

    /**
     * 金币数
     */
    private Integer points;

    /**
     * 0不运行，1运行，-1删除
     */
    private Integer status = 1;

    /**
     * 签到次数
     */
    private Integer executedTimes = 1;


    private static final long serialVersionUID = 1L;

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

    public String getUserSlag() {
        return userSlag;
    }

    public void setUserSlag(String userSlag) {
        this.userSlag = userSlag;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getFinishedContests() {
        return finishedContests;
    }

    public void setFinishedContests(Integer finishedContests) {
        this.finishedContests = finishedContests;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getGlobalRank() {
        return globalRank;
    }

    public void setGlobalRank(String globalRank) {
        this.globalRank = globalRank;
    }

    public String getSolvedQuestion() {
        return solvedQuestion;
    }

    public void setSolvedQuestion(String solvedQuestion) {
        this.solvedQuestion = solvedQuestion;
    }

    public String getAcceptedSubmission() {
        return acceptedSubmission;
    }

    public void setAcceptedSubmission(String acceptedSubmission) {
        this.acceptedSubmission = acceptedSubmission;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
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
            case -1:
                return Status.DELETED;
            case 0:
                return Status.STOPPED;
            case 1:
                return Status.RUNNING;
            default:
                throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "status参数错误");
        }
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LeetcodeInfo other = (LeetcodeInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getUserSlag() == null ? other.getUserSlag() == null : this.getUserSlag().equals(other.getUserSlag()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getSchool() == null ? other.getSchool() == null : this.getSchool().equals(other.getSchool()))
            && (this.getFinishedContests() == null ? other.getFinishedContests() == null : this.getFinishedContests().equals(other.getFinishedContests()))
            && (this.getRating() == null ? other.getRating() == null : this.getRating().equals(other.getRating()))
            && (this.getGlobalRank() == null ? other.getGlobalRank() == null : this.getGlobalRank().equals(other.getGlobalRank()))
            && (this.getSolvedQuestion() == null ? other.getSolvedQuestion() == null : this.getSolvedQuestion().equals(other.getSolvedQuestion()))
            && (this.getAcceptedSubmission() == null ? other.getAcceptedSubmission() == null : this.getAcceptedSubmission().equals(other.getAcceptedSubmission()))
            && (this.getPoints() == null ? other.getPoints() == null : this.getPoints().equals(other.getPoints()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExecutedTimes() == null ? other.getExecutedTimes() == null : this.getExecutedTimes().equals(other.getExecutedTimes()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getUserSlag() == null) ? 0 : getUserSlag().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getSchool() == null) ? 0 : getSchool().hashCode());
        result = prime * result + ((getFinishedContests() == null) ? 0 : getFinishedContests().hashCode());
        result = prime * result + ((getRating() == null) ? 0 : getRating().hashCode());
        result = prime * result + ((getGlobalRank() == null) ? 0 : getGlobalRank().hashCode());
        result = prime * result + ((getSolvedQuestion() == null) ? 0 : getSolvedQuestion().hashCode());
        result = prime * result + ((getAcceptedSubmission() == null) ? 0 : getAcceptedSubmission().hashCode());
        result = prime * result + ((getPoints() == null) ? 0 : getPoints().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExecutedTimes() == null) ? 0 : getExecutedTimes().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", userSlag=").append(userSlag);
        sb.append(", password=").append(password);
        sb.append(", realName=").append(realName);
        sb.append(", avatar=").append(avatar);
        sb.append(", location=").append(location);
        sb.append(", school=").append(school);
        sb.append(", finishedContests=").append(finishedContests);
        sb.append(", rating=").append(rating);
        sb.append(", globalRank=").append(globalRank);
        sb.append(", solvedQuestion=").append(solvedQuestion);
        sb.append(", acceptedSubmission=").append(acceptedSubmission);
        sb.append(", points=").append(points);
        sb.append(", status=").append(status);
        sb.append(", executedTimes=").append(executedTimes);
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", updateTime=").append(getUpdateTime());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}