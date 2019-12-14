package com.hukun.exam.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class ExamUser {

    Integer id;
    String examName;
    String examDecript;
    Integer classid;
    Integer categary;
    Integer publish;
    Integer teacherId;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss") //fastJson 格式化时间
    Date publishTime;
    Integer examStauts;

    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDecript() {
        return examDecript;
    }

    public void setExamDecript(String examDecript) {
        this.examDecript = examDecript;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getCategary() {
        return categary;
    }

    public void setCategary(Integer categary) {
        this.categary = categary;
    }

    public Integer getPublish() {
        return publish;
    }

    public void setPublish(Integer publish) {
        this.publish = publish;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getExamStauts() {
        return examStauts;
    }

    public void setExamStauts(Integer examStauts) {
        this.examStauts = examStauts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
