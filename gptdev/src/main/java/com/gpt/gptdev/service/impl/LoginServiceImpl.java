package com.gpt.gptdev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpt.gptdev.entity.userinformation;
import com.gpt.gptdev.mapper.UserMapper;
import com.gpt.gptdev.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper,userinformation> implements LoginService {
    @Override
    public boolean saveemail(String email) {
        QueryWrapper<userinformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email", email);
        Long integer = baseMapper.selectCount(queryWrapper);
        if(integer>0){
            return true;
        }

        userinformation user = new userinformation();
        user.setUser_email(email);
        user.setNickname(email);
        user.setId(0);
        int insert = baseMapper.insert(user);
        if (insert==1){
            return true;
        }
        return false;
    }

    @Override
    public void saverealname(String realname) {
        
    }

    @Override
    public void saveid(int id) {

    }
}
