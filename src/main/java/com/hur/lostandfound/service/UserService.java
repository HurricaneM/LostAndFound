package com.hur.lostandfound.service;

import com.hur.lostandfound.bean.User;
import com.hur.lostandfound.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserService {
    public void insertUser(User user);
    public List<User> selectAllUser();
    public User selectOneByExp(User user);
}
