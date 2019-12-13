package com.hukun.exam.controller;

import com.hukun.exam.pojo.*;
import com.hukun.exam.service.UserDao;
import com.hukun.exam.util.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    UserDao userDao;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("login.action")
    @ResponseBody
    public UserRight loginAction(@RequestBody User user) {
        UserRight loginUser = userDao.Login(user);
        if (null != loginUser) {
            HttpSession session = request.getSession();
            session.setAttribute("user", loginUser);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", loginUser.getId());
            map.put("userEndTime", new Date());
            userDao.loginDateTime(map);
        }
        return loginUser;
    }

    @RequestMapping("userCount.action")
    @ResponseBody
    public int userCount() {
        return userDao.userCount(null);

    }

    @RequestMapping("userList.action")
    @ResponseBody
    public Map<String, Object> getUserList(User user, int page, int limit) {
        HashMap<String, Object> paramMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        int pageStart = (page - 1) * limit;
        paramMap.put("pageStart", pageStart);
        paramMap.put("pageSize", limit);
        paramMap.put("nickname", user.getNickname());
        List<User> users = userDao.getUserList(paramMap);


        map.put("code", 0);
        map.put("msg", "");
        map.put("count", userDao.userCount(user));
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONArray userMap = JSONArray.fromObject(users, jsonConfig);
        map.put("data", userMap);
        return map;
    }


    @RequestMapping("modifyUserSelf.action")
    @ResponseBody
    public UserRight modifyUserSelf(@RequestBody QueryUserVo user) {
        int update = userDao.modifyUserSelf(user);
        UserRight userByid = null;
        if (update > 0) {
            userByid = userDao.getUserByid(user.getId());
        }
        return userByid;
    }


    @RequestMapping("uploadHead.action")
    @ResponseBody
    public UserRight uploadHead(MultipartFile file, int id) throws IOException {
        String filename = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        filename = filename + "." + extension;
        String path = "D:\\upload\\";
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user.setId(id);
        user.setHeadPath(filename);
        file.transferTo(new File(path + filename));
        int update = userDao.updateHead(user);
        UserRight userByid = userDao.getUserByid(id);
        return userByid;
    }


    @RequestMapping("getClasses.action")
    @ResponseBody
    public List<Classes> getLClassesByid() {
        return userDao.selectClassByid();
    }

    @RequestMapping("getUserByid.action")
    @ResponseBody
    public UserRight getUserByid(int id) {
        return userDao.getUserByid(id);
    }

    @RequestMapping("/modifyUser.action")
    @ResponseBody
    public int modifyUser(User user) {
        return userDao.modifyUser(user);
    }

    @RequestMapping("getClasses2.action")
    @ResponseBody
    public List<Classes> getClasses2() {
        List<Classes> classes = userDao.getClasses();
        return classes;
    }


}
