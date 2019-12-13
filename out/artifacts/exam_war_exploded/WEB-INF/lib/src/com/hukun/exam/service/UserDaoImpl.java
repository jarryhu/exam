package com.hukun.exam.service;

import com.hukun.exam.mapper.UserMapper;

import com.hukun.exam.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;

@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserRight Login(User user) {
        return userMapper.login(user);
    }

    @Override
    public int updateUserinfo(User user) {
        return 0;
    }

    @Override
    public int deleteUser(Integer id) {
        return 0;
    }

    @Override
    public int userAdd(User user) {
        return 0;
    }

    @Override
    public List<User> getUserList(HashMap map) {
        return userMapper.getUserList(map);
    }


    @Override
    public int updateHead(User user) {
        return userMapper.updateHead(user);
    }

    @Override
    public int checkUserName(String name) {
        return 0;
    }


    @Override
    public int userCount(User user) {
        return userMapper.userCount(user);
    }

    @Override
    public List<User> getUserList(User user) {
        return null;
    }


    @Override
    public List<User> selectpage(HashMap map) {
        return null;
    }

    @Override
    public int updateuser(User user) {
        return 0;
    }

    @Override
    public int loginDateTime(HashMap map) {
        return userMapper.loginDateTime(map);
    }

    @Override
    public List<Classes> selectClassByid() {
        return userMapper.selectClassByid();
    }


    @Override
    public int modifyUserSelf(QueryUserVo user) {
        return userMapper.modifyUserSelf(user);
    }


    @Override
    public UserRight getUserByid(int id) {
        return userMapper.getUserByid(id);
    }

    @Override
    public int modifyUser(User user) {
        return userMapper.modifyUser(user);
    }

    @Override
    public List<Classes> getClasses() {
        return userMapper.getClasses();
    }

}
