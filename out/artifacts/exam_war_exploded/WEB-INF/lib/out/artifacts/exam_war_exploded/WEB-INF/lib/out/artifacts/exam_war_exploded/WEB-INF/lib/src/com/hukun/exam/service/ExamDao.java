package com.hukun.exam.service;

import com.hukun.exam.pojo.Exam;
import com.hukun.exam.pojo.ExamMark;
import com.hukun.exam.pojo.ExamUser;

import com.hukun.exam.pojo.Paper;

import java.util.HashMap;
import java.util.List;

public interface ExamDao {
    public List<ExamUser> getExamList(HashMap map);

    // public int insertPaper(Paper paper);
    public int insertExam(Exam exam);

    public int updateExamFile(Exam exam);

    public int insertPaper(List<Paper> list);

    public List<ExamMark> getStudentExam(HashMap map);

    public List<Paper> makePaper(Integer examid);
}
