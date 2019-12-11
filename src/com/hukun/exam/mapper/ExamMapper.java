package com.hukun.exam.mapper;


import com.hukun.exam.pojo.*;

import java.util.HashMap;
import java.util.List;


public interface ExamMapper {

    public List<ExamUser> getExamList(HashMap map);

    public int insertPaper(Paper paper);

    public int insertExam(Exam exam);

}
