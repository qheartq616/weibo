package org.example.weibo.pojo;

import java.util.Date;

public class Follow {
    private Integer fid;

    private Integer uid;

    private Integer followUid;

    private Date followTime;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFollowUid() {
        return followUid;
    }

    public void setFollowUid(Integer followUid) {
        this.followUid = followUid;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }
}