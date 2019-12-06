package com.hukun.exam.controller;


import com.hukun.exam.exception.HandleForException;
import com.hukun.exam.pojo.QueryUserVo;
import com.hukun.exam.pojo.User;
import com.hukun.exam.service.UserDao;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


}
