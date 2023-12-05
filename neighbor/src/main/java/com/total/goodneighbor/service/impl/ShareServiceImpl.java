package com.total.goodneighbor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.total.goodneighbor.entity.things;
import com.total.goodneighbor.mapper.ShareMapper;
import com.total.goodneighbor.mapper.UserMapper;
import com.total.goodneighbor.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, things> implements ShareService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String getimage(String email,String tname) {
        System.out.println("所查共享物品的邮箱为"+email);
        LambdaQueryWrapper<things> emailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        emailLambdaQueryWrapper.eq(things::getEmail,email);
        emailLambdaQueryWrapper.eq(things::getTname,tname);
        return baseMapper.selectOne(emailLambdaQueryWrapper).getUrl();
    }

    @Override
    public int getstatus(String email, String tname) {
        System.out.println("所查共享物品的邮箱为"+email+"共享物品名称为:"+tname);
        LambdaQueryWrapper<things> emailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        emailLambdaQueryWrapper.eq(things::getEmail,email);
        emailLambdaQueryWrapper.eq(things::getTname,tname);
        return baseMapper.selectOne(emailLambdaQueryWrapper).getStatus();
    }

    @Override
    public String saveshare(String email, String tname) {
        return null;
    }
}
