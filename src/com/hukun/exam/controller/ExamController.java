package com.hukun.exam.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hukun.exam.pojo.*;
import com.hukun.exam.service.ExamDao;
import com.hukun.exam.util.JsonDateValueProcessor;
import com.hukun.exam.util.ToolUtil;

import org.apache.commons.io.FilenameUtils;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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


    @RequestMapping("deleteExam.action")
    @ResponseBody
    public int deleteExam(Integer id) {
        return examDao.deleteExam(id);
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
        int result = 0;
        try {

            List<Paper> list = ToolUtil.readExcel(path, examid);
            result = examDao.insertPaper(list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }

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


    @RequestMapping("/makePaper.action")
    @ResponseBody
    public List<Paper> makePaper(int examid) {
        List<Paper> papers = examDao.makePaper(examid);
//        List<PaperAsOption> paperAsOptions = new ArrayList<>();
//        for (Paper p :
//                papers) {
//            String options = p.getOptions_();
//            String[] strings = options.split(";");
//            PaperAsOption.OptionsBean optionsBean = new PaperAsOption.OptionsBean();
//            PaperAsOption paperAsOption = new PaperAsOption();
//            paperAsOption.setId(p.getId());
//            paperAsOption.setTitle(p.getTitle());
//            paperAsOption.setExamid(p.getExamid());
//            optionsBean.setA(strings[0].substring(2));
//            optionsBean.setB(strings[1].substring(2));
//            optionsBean.setC(strings[2].substring(2));
//            optionsBean.setD(strings[3].substring(2));
//            paperAsOption.setOptions_(optionsBean);
//            paperAsOptions.add(paperAsOption);
        //       }
        return papers;
    }

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/advance.action")
    @ResponseBody
    public int advance(@RequestParam(value = "choosex[]") String[] choosex, int examid, int userid) {
        //   for (int i = 0; i < choice.size(); i++) {
        int total = 0;
        List<String> getanswer = examDao.getAnswer(examid);
        int avage = 100 / getanswer.size();//每道题的分数
        //String[] split = choices.split(",");
        for (int i = 0; i < getanswer.size(); i++) {
            String a = getanswer.get(i).toLowerCase();
            if (a.equals(choosex[i])) {
                total += avage;
            }
        }
        //插入成绩
        Marklist marklist = new Marklist();
        marklist.setExamid(examid);
        marklist.setMark(total);
        marklist.setUserid(userid);
        int result = examDao.scoreInsert(marklist);
        System.out.println(total);

        return result;
    }


}
