package com.hur.lostandfound.service.serviceImpl;

import com.hur.lostandfound.bean.User;
import com.hur.lostandfound.dao.UserMapper;
import com.hur.lostandfound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public User selectOneByExp(User user) {
        return userMapper.selectOne(user);
    }
}
