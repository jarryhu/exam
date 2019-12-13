package com.hukun.exam.pojo;

public class Marklist {
    private Integer id;
    private Integer examid;
    private Integer mark;
    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamid() {
        return examid;
    }

    public void setExamid(Integer examid) {
        this.examid = examid;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Marklist{" +
                "id=" + id +
                ", examid=" + examid +
                ", mark=" + mark +
                ", userid=" + userid +
                '}';
    }
}
