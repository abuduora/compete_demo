package com.gpt.gptdev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpt.gptdev.entity.User;
import com.gpt.gptdev.mapper.UserMapper;
import com.gpt.gptdev.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {
    @Override
    public boolean saveUser(String email) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        Long integer = baseMapper.selectCount(queryWrapper);
        if(integer>0){
            return true;
        }


        User user = new User();
        user.setEmail(email);
        user.setUsername(email);
        user.setIntegral(0);
        int insert = baseMapper.insert(user);
        if (insert==1){
            return true;
        }
        return false;
    }

}
