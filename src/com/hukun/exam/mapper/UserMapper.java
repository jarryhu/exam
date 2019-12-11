package com.hukun.exam.mapper;


import com.hukun.exam.pojo.*;

import java.util.HashMap;
import java.util.List;


public interface UserMapper {

    public UserRight login(User user);

    public List<User> getUserList(HashMap map);

    public int modifyUserSelf(QueryUserVo user);

    public User getUserByid(int id);

    public int updateHead(User user);

    public int userCount(User user);

    public int loginDateTime(HashMap map);

    public List<Classes> selectClassByid();

    public int modifyUser(User user);




}
