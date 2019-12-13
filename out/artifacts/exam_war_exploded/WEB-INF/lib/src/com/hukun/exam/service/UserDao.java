package com.hukun.exam.service;

import com.hukun.exam.pojo.*;

import java.util.HashMap;
import java.util.List;

public interface UserDao {

    public UserRight Login(User user);

    public int updateUserinfo(User user);

    public int deleteUser(Integer id);

    public int userAdd(User user);

    public List<User> getUserList(HashMap map);

    public UserRight getUserByid(int id);

    public int updateHead(User user);

    public int checkUserName(String name);

    public int userCount(User user);


    public List<User> getUserList(User user);

    public List<User> selectpage(HashMap map);

    public int updateuser(User user);


    public int loginDateTime(HashMap map);

    public List<Classes> selectClassByid();

    public int modifyUserSelf(QueryUserVo user);

    public int modifyUser(User user);

    public List<Classes> getClasses();


}
