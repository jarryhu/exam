package com.hukun.exam.mapper;


import com.hukun.exam.pojo.*;

import java.util.HashMap;
import java.util.List;


public interface ExamMapper {

    public List<ExamUser> getExamList(HashMap map);

    public int insertPaper(Paper paper);

    public int insertExam(Exam exam);

    public int updateExamFile(Exam exam);

    public int insertPaper(List<Paper> list);

    public List<ExamMark> getStudentExam(HashMap map);

    public List<Paper> makePaper(Integer examid);

    public List<String> getAnswer(Integer examid);

    public int scoreInsert(Marklist marklist);
}
