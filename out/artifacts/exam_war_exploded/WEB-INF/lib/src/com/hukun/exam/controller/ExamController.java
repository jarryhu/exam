package com.hukun.exam.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hukun.exam.pojo.*;
import com.hukun.exam.service.ExamDao;
import com.hukun.exam.util.JsonDateValueProcessor;
import com.hukun.exam.util.ToolUtil;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class ExamController {
    @Autowired
    ExamDao examDao;

    /**
     * 显示所剖考试列表
     *
     * @param id
     * @param page
     * @param limit
     * @return
     */
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
        JSONArray array = (JSONArray) JSON.toJSON(examList);
        map.put("data", array);

        return map;
    }

    /**
     * 发布考试
     *
     * @param exam
     * @return
     */
    @RequestMapping("publishExam.action")
    @ResponseBody
    public int publishExam(Exam exam) {
        exam.setPublishTime(new Date());
        return examDao.insertExam(exam);
    }

    /**
     * 考试附件的上传
     *
     * @param file
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping("uploadExam.action")
    @ResponseBody
    public Map<String, String> uploadExam(MultipartFile file, int id) throws IOException {
        String filename = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        filename = filename + "." + extension;
        String path = "D:\\upload\\";
        Map<String, String> map = new HashMap<>();
        Exam exam = new Exam();
        exam.setId(id);
        exam.setExamFilePath(filename);
        file.transferTo(new File(path + filename));
        int update = examDao.updateExamFile(exam);
        if (update > 0) {
            map.put("path", path + filename);
        }
        return map;

    }

    @RequestMapping("insertPaper.action")
    @ResponseBody
    public int insertPaper(String path, int examid) {
        try {

            List<Paper> list = ToolUtil.readExcel(path, examid);
            int result = examDao.insertPaper(list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @RequestMapping("getStudentExam.action")
    @ResponseBody
    public HashMap<String, Object> getStudentExam(int id, int classid, int page, int limit) {

        HashMap<String, Object> paramMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        int pageStart = (page - 1) * limit;
        paramMap.put("pageStart", pageStart);
        paramMap.put("pageSize", limit);
        paramMap.put("id", id);
        paramMap.put("classid", classid);
        List<ExamMark> results = examDao.getStudentExam(paramMap);
        map.put("count", results.size());
        JSONArray array = (JSONArray) JSON.toJSON(results);
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", array);
        return map;

    }


    @RequestMapping("makePaper.action")
    @ResponseBody
    public List<Paper> makePaper(int examid) {
        return examDao.makePaper(examid);
    }


    @RequestMapping("/advance.action")
    @ResponseBody
    public int advance(String choices) {
        String answer = choices.substring(0, choices.length() - 1);
        //   for (int i = 0; i < choice.size(); i++) {
        System.out.println(answer);
        // }
        return 1;
    }


}
