package com.hukun.exam.pojo;

public class Classes extends Exam {

    private Integer id;
    private String className;
    private String grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

}
