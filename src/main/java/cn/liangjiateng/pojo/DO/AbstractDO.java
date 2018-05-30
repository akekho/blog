package cn.liangjiateng.pojo.DO;

import java.sql.Timestamp;

/**
 * Created by Jiateng on 5/28/18.
 */
public abstract class AbstractDO {

    private Timestamp createTime;
    private Timestamp updateTime;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
