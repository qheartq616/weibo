package org.example.weibo.pojo;

import java.util.Date;

public class User {
    private Integer uid;

    private String name;

    private String password;

    private String image;

    private String email;

    private String gender;

    private Date registerTime;

    private Date updateNameTime;

    private String description;

    private Date birthday;

    private String city;

    private Integer status;

    private Long phone;

    private int countAllFollowUser;

    private int countAllFans;

    private int countAllLike;

    private int countAllPost;

    private int followStatus;

    public int getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(int followStatus) {
        this.followStatus = followStatus;
    }

    public int getCountAllFollowUser() {
        return countAllFollowUser;
    }

    public void setCountAllFollowUser(int countAllFollowUser) {
        this.countAllFollowUser = countAllFollowUser;
    }

    public int getCountAllFans() {
        return countAllFans;
    }

    public void setCountAllFans(int countAllFans) {
        this.countAllFans = countAllFans;
    }

    public int getCountAllLike() {
        return countAllLike;
    }

    public void setCountAllLike(int countAllLike) {
        this.countAllLike = countAllLike;
    }

    public int getCountAllPost() {
        return countAllPost;
    }

    public void setCountAllPost(int countAllPost) {
        this.countAllPost = countAllPost;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getUpdateNameTime() {
        return updateNameTime;
    }

    public void setUpdateNameTime(Date updateNameTime) {
        this.updateNameTime = updateNameTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}