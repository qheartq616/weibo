package org.example.weibo.pojo;

import java.util.Date;
import java.util.List;

public class Post {
    private Integer pid;

    private String forwardPid;

    private Integer uid;//发微博的用户

    private String text;

    private String picture;

    private Date postTime;

    private Integer status;

    private Integer countForward;

    //发这条微博的用户
    private User user;

    private boolean postLike;

    private int countPostLike;

    private List<Comment> commentList;

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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getForwardPid() {
        return forwardPid;
    }

    public void setForwardPid(String forwardPid) {
        this.forwardPid = forwardPid == null ? null : forwardPid.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCountForward() {
        return countForward;
    }

    public void setCountForward(Integer countForward) {
        this.countForward = countForward;
    }
}