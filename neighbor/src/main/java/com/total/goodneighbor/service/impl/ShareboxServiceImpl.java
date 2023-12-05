package com.total.goodneighbor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.total.goodneighbor.entity.share_box;
import com.total.goodneighbor.mapper.ShareboxMapper;
import com.total.goodneighbor.service.ShareboxService;
import org.springframework.stereotype.Service;

@Service
public class ShareboxServiceImpl extends ServiceImpl<ShareboxMapper, share_box> implements ShareboxService {
    @Override
    public boolean have(String community, int box_id) {
        LambdaQueryWrapper<share_box> objectLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper1.eq(share_box::getCommunity,community);
        objectLambdaQueryWrapper1.eq(share_box::getBox_id,box_id);
        if(baseMapper.selectOne(objectLambdaQueryWrapper1).getHave()==0)
            return false;
        return true;
    }
}
