package org.example.weibo.pojo;

import java.util.Date;

public class PostLike {
    private Integer lid;

    private Integer uid;

    private String upid;

    private Date likeTime;

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUpid() {
        return upid;
    }

    public void setUpid(String upid) {
        this.upid = upid == null ? null : upid.trim();
    }

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }
}