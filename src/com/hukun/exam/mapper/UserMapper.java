package com.hukun.exam.mapper;


import com.hukun.exam.pojo.Classes;
import com.hukun.exam.pojo.QueryUserVo;
import com.hukun.exam.pojo.User;

import java.util.HashMap;
import java.util.List;


public interface UserMapper {

    public User login(User user);

    public List<User> getUserList(HashMap map);

    public int modifyUserSelf(QueryUserVo user);

    public User getUserByid(int id);

    public int updateHead(User user);

    public int userCount(User user);

    public int loginDateTime(HashMap map);

    public List<Classes> selectClassByid();

    public int modifyUser(User user);


}
