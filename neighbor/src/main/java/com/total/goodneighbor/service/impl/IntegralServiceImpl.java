package com.total.goodneighbor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.total.goodneighbor.entity.integral;
import com.total.goodneighbor.entity.userinformation;
import com.total.goodneighbor.mapper.IntegralMapper;
import com.total.goodneighbor.mapper.UserMapper;
import com.total.goodneighbor.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IntegralServiceImpl extends ServiceImpl<IntegralMapper, integral> implements IntegralService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Object> rank(String community) {
        LambdaQueryWrapper<integral> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<userinformation> avatar= new LambdaQueryWrapper<>();
        LambdaQueryWrapper<userinformation> nickname= new LambdaQueryWrapper<>();
        LambdaQueryWrapper<userinformation> buildnumber = new LambdaQueryWrapper<>();
        List<integral> List;
        List<String> email = new ArrayList<>();
        List<Integer> integrallist = new ArrayList<>();
        List<String> avatarlist = new ArrayList<>();
        List<String> nicknamelist = new ArrayList<>();
        List<String> buildnumerlist = new ArrayList<>();
        List<Object> total = new ArrayList<>();
        for(int j=1000,count=0;j>=0;j--){
            objectLambdaQueryWrapper.eq(integral::getIntegral,j);
            objectLambdaQueryWrapper.eq(integral::getCommunity_name,community);
            if(baseMapper.selectList(objectLambdaQueryWrapper)!=null){
                List=baseMapper.selectList(objectLambdaQueryWrapper);
                integrallist.add(List.get(count).getIntegral());
                email.add(List.get(count).getUser_email());
                count++;
                if(count==4)
                    break;
            }
        }
        for(int i=0;i<=4;i++) {
            avatar.eq(userinformation::getUser_email, email.get(i));
            avatarlist.add(userMapper.selectOne(avatar).getAvatar());

            nickname.eq(userinformation::getUser_email, email.get(i));
            nicknamelist.add(userMapper.selectOne(nickname).getNickname());

            buildnumber.eq(userinformation::getUser_email, email.get(i));
            buildnumerlist.add(userMapper.selectOne(buildnumber).getBuilding_number());
        }

        for(int i=0;i<=4;i++){
            total.add(avatarlist.get(i));
        }
        for(int i=0;i<=4;i++){
            total.add(nicknamelist.get(i));
        }
        for(int i=0;i<=4;i++){
            total.add(buildnumerlist.get(i));
        }
        return total;
    }
}
