package com.total.goodneighbor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.total.goodneighbor.entity.userinformation;
import com.total.goodneighbor.mapper.UserMapper;
import com.total.goodneighbor.service.ShareService;
import org.springframework.stereotype.Service;

@Service
public class ShareServiceImpl extends ServiceImpl<UserMapper,userinformation> implements ShareService {
}
