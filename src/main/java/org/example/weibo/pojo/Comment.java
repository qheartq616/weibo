package org.example.weibo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Comment {
    private Integer cid;

    private Integer uid;

    private String upid;

    private String upcid;

    private String comment;

    private  Post post;

    @JsonFormat(pattern = "MM月dd日 HH:mm",timezone="GMT+8")
    private Date commentTime;

    //发评论的用户
    private User user;

    private int countCommentLike;

    private List<Comment> subCommentList;

    private boolean commentLike;

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", upid='" + upid + '\'' +
                ", upcid='" + upcid + '\'' +
                ", comment='" + comment + '\'' +
                ", commentTime=" + commentTime +
                ", user=" + user +
                ", countCommentLike=" + countCommentLike +
                ", subCommentList=" + subCommentList +
                ", commentLike=" + commentLike +
                '}';
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Comment> getSubCommentList() {
        return subCommentList;
    }

    public void setSubCommentList(List<Comment> subCommentList) {
        this.subCommentList = subCommentList;
    }

    public int getCountCommentLike() {
        return countCommentLike;
    }

    public void setCountCommentLike(int countCommentLike) {
        this.countCommentLike = countCommentLike;
    }

    public boolean isCommentLike() {
        return commentLike;
    }

    public void setCommentLike(boolean commentLike) {
        this.commentLike = commentLike;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

    public String getUpcid() {
        return upcid;
    }

    public void setUpcid(String upcid) {
        this.upcid = upcid == null ? null : upcid.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}