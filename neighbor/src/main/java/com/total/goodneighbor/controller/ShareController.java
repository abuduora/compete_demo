package com.total.goodneighbor.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.total.goodneighbor.entity.userinformation;
import com.total.goodneighbor.mapper.UserMapper;
import com.total.goodneighbor.service.ShareService;
import com.total.goodneighbor.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/share")
public class ShareController extends ServiceImpl<UserMapper, userinformation> {
    @Autowired
    private ShareService shareService;

    @PostMapping("/opendoor")
    public String opendoor(@RequestBody String email) {
        int i=1;
        String data = Integer.toString(i);
        return HttpUtil.post("http://[240e:404:b701:8df3:cd23:aeb6:46a9:f112]:8080/receive_data",data,new HashMap<>());
    }

    @PostMapping("/justopendoor")
    public String justopendoor(@RequestBody String email) {
        int i=2;
        String data = Integer.toString(i);
        return HttpUtil.post("http://[240e:404:b701:8df3:cd23:aeb6:46a9:f112]:8080/receive_data",data,new HashMap<>());
    }
}
