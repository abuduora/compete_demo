package com.total.goodneighbor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.total.goodneighbor.entity.userinformation;
import com.total.goodneighbor.mapper.UserMapper;
import com.total.goodneighbor.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,userinformation> implements UserService {
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
        user.setId("0");
        int insert = baseMapper.insert(user);
        if (insert==1){
            return true;
        }
        return false;
    }

    @Override
    public userinformation saverealname(String email,String realname,String id) {
        System.out.println("实名接受email"+email);
        userinformation list = baseMapper.selectById(email);
        System.out.println("刚查出来的list"+list);
        list.setUser_email(email);
        list.setRealname(realname);
        list.setId(id);
        baseMapper.updateById(list);
        System.out.println(id);
        System.out.println(id);
        System.out.println("修改之后的list"+list);
        return list;
    }

    @Override
    public userinformation savemessage(String email, String nickname, String sex, String phone,String buildingnumber,String address) {
        userinformation list = baseMapper.selectById(email);
        list.setUser_email(email);
        list.setNickname(nickname);
        list.setSex(sex);
        list.setPhone(phone);
        list.setBuilding_number(buildingnumber);
        list.setUser_address(address);
        baseMapper.updateById(list);
        System.out.println(list);
        return list;
    }

    @Override
    public int integral(String email) {
        System.out.println("所查积分的邮箱为"+email);
        LambdaQueryWrapper<userinformation> emailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        emailLambdaQueryWrapper.eq(userinformation::getUser_email,email);
        return baseMapper.selectOne(emailLambdaQueryWrapper).getIntegral();
    }
}
