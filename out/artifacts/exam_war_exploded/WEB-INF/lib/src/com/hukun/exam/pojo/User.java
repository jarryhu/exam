package com.hukun.exam.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class User {
    private Integer id;
    private String nickname;
    private String loginName;
    private String password;
    private Integer right;
    private Integer stauts;
    private String headPath;
    private Integer classid;
    private String userEmail;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss") //fastJson 格式化时间
    private Date userEndTime;
    private String userPhone;
    private Date userBirthday;
    private int province;
    private int city;
    private String myself;
    private String sex;
    private int area;

    private String userHobby;


    public String getUserHobby() {
        return userHobby;
    }

    public void setUserHobby(String userHobby) {
        this.userHobby = userHobby;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserEndTime() {
        return userEndTime;
    }

    public void setUserEndTime(Date userEndTime) {
        this.userEndTime = userEndTime;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getMyself() {
        return myself;
    }

    public void setMyself(String myself) {
        this.myself = myself;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", right=" + right +
                ", stauts=" + stauts +
                ", headPath='" + headPath + '\'' +
                ", classid=" + classid +
                ", userEmail='" + userEmail + '\'' +
                ", userEndTime=" + userEndTime +
                ", userPhone='" + userPhone + '\'' +
                ", userBirthday=" + userBirthday +
                ", province=" + province +
                ", city=" + city +
                ", myself='" + myself + '\'' +
                ", sex='" + sex + '\'' +
                ", area=" + area +
                ", userHobby='" + userHobby + '\'' +
                '}';
    }
}
