package com.hukun.exam.mapper;


import com.hukun.exam.pojo.QueryUserVo;
import com.hukun.exam.pojo.User;

import java.util.HashMap;
import java.util.List;


public interface UserMapper {

    public User login(User user);

    public List<User> userList(User user);

    public int modifyUser(QueryUserVo user);

    public User getUserByid(int id);


}
