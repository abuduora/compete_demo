package com.gpt.gptdev.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.gpt.gptdev.entity.User;

import com.gpt.gptdev.mapper.UserMapper;

import com.gpt.gptdev.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public List<User> getUser(String user_id) {
        List<User> users = baseMapper.selectList(null);
        return users;
    }
}
