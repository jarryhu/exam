package com.hukun.exam.controller;


import com.hukun.exam.exception.HandleForException;
import com.hukun.exam.pojo.QueryUserVo;
import com.hukun.exam.pojo.User;
import com.hukun.exam.service.UserDao;
import net.sf.json.JSONArray;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping("login.action")
    @ResponseBody
    public User loginAction(@RequestBody User user) {
        User loginUser = userDao.Login(user);
        return loginUser;
    }

    @RequestMapping("userList.action")
    @ResponseBody
    public Map<String, Object> userList(User user) {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userDao.userList(user);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", users.size());
        JSONArray userMap = JSONArray.fromObject(users);
        map.put("data", userMap);
        return map;
    }


    @RequestMapping("modifyUser.action")
    @ResponseBody
    public User modifyUser(@RequestBody QueryUserVo user) {
        int update = userDao.modifyUser(user);
        User userByid = null;
        if (update > 0) {
            userByid = userDao.getUserByid(user.getId());
        }
        return userByid;
    }


    @RequestMapping("uploadHead.action")
    @ResponseBody
    public User uploadHead(MultipartFile file, int id) throws IOException {
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
        User userByid = userDao.getUserByid(id);

        return userByid;


    }


}
