package org.example.weibo.pojo;

import java.util.Date;

public class Post {
    private Integer pid;

    private Integer forwardPid;

    private Integer uid;

    private String text;

    private String picture;

    private Date postTime;

    private Date updateTime;

    private Integer status;

    private User user;

    private boolean postLike;

    private int countPostLike;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPostLike() {
        return postLike;
    }

    public void setPostLike(boolean postLike) {
        this.postLike = postLike;
    }

    public int getCountPostLike() {
        return countPostLike;
    }

    public void setCountPostLike(int countPostLike) {
        this.countPostLike = countPostLike;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getForwardPid() {
        return forwardPid;
    }

    public void setForwardPid(Integer forwardPid) {
        this.forwardPid = forwardPid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}