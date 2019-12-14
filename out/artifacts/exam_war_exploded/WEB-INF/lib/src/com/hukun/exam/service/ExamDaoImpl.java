package com.hukun.exam.service;

import com.hukun.exam.mapper.ExamMapper;
import com.hukun.exam.mapper.UserMapper;
import com.hukun.exam.pojo.*;

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
    public int insertExam(Exam exam) {
        examMapper.insertExam(exam);
        return exam.getId();
    }

    @Override
    public int updateExamFile(Exam exam) {
        return examMapper.updateExamFile(exam);
    }

    @Override
    public int insertPaper(List<Paper> list) {
        return examMapper.insertPaper(list);
    }


    public List<ExamMark> getStudentExam(HashMap map) {
        return examMapper.getStudentExam(map);
    }

    public List<Paper> makePaper(Integer examid) {
        return examMapper.makePaper(examid);
    }

    public List<String> getAnswer(Integer examid) {
        return examMapper.getAnswer(examid);
    }

    public int scoreInsert(Marklist marklist) {
        return examMapper.scoreInsert(marklist);
    }
}
