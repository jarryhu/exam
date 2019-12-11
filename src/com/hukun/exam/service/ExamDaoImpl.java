package com.hukun.exam.service;

import com.hukun.exam.mapper.ExamMapper;
import com.hukun.exam.mapper.UserMapper;
import com.hukun.exam.pojo.Exam;
import com.hukun.exam.pojo.ExamUser;
import com.hukun.exam.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ExamDaoImpl implements ExamDao {
    @Autowired
    ExamMapper examMapper;

    @Override
    public List<ExamUser> getExamList(HashMap map) {
        return examMapper.getExamList(map);
    }

    @Override
    public int insertPaper(Paper paper) {
        return examMapper.insertPaper(paper);
    }

    @Override
    public int insertExam(Exam exam) {
        return examMapper.insertExam(exam);
    }
}
