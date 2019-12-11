package com.hukun.exam.controller;

import com.hukun.exam.pojo.Exam;
import com.hukun.exam.pojo.ExamUser;
import com.hukun.exam.pojo.Paper;
import com.hukun.exam.service.ExamDao;
import com.hukun.exam.util.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ExamController {
    @Autowired
    ExamDao examDao;


    @RequestMapping("getExamList.action")
    @ResponseBody
    public HashMap<String, Object> getExamList(int id, int page, int limit) {
        HashMap<String, Object> paramMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        int pageStart = (page - 1) * limit;
        paramMap.put("pageStart", pageStart);
        paramMap.put("pageSize", limit);
        paramMap.put("id", id);
        List<ExamUser> examList = examDao.getExamList(paramMap);

        map.put("code", 0);
        map.put("msg", "");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONArray examsMap = JSONArray.fromObject(examList, jsonConfig);
        map.put("data", examsMap);
        return map;
    }

    @RequestMapping("publishExam.action")
    @ResponseBody
    public int publishExam(Exam exam) {
        return examDao.insertExam(exam);
    }
}
