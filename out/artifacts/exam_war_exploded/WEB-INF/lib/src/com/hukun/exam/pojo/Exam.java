package com.hukun.exam.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class Exam implements Serializable {

    Integer id;
    protected String examName;
    String examDecript;
    Integer score;
    String time;
    Integer classid;
    Integer categary;
    Integer publish;
    Integer teacherId;
    Date publishTime;
    Integer examStauts;
    String examFilePath;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExamFilePath() {
        return examFilePath;
    }

    public void setExamFilePath(String examFilePath) {
        this.examFilePath = examFilePath;
    }

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

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", examName='" + examName + '\'' +
                ", examDecript='" + examDecript + '\'' +
                ", classid=" + classid +
                ", categary=" + categary +
                ", publish=" + publish +
                ", teacherId=" + teacherId +
                ", publishTime=" + publishTime +
                ", examStauts=" + examStauts +
                ", examFilePath='" + examFilePath + '\'' +
                '}';
    }
}
