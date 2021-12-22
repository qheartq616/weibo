package org.example.weibo.pojo;

import java.util.Date;

public class CommentLike {
    private Integer clid;

    private Integer uid;

    private String ucid;

    private Date likeTime;

    public Integer getClid() {
        return clid;
    }

    public void setClid(Integer clid) {
        this.clid = clid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUcid() {
        return ucid;
    }

    public void setUcid(String ucid) {
        this.ucid = ucid == null ? null : ucid.trim();
    }

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }
}