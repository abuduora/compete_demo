package com.total.goodneighbor.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.total.goodneighbor.entity.userinformation;
import com.total.goodneighbor.mapper.UserMapper;
import com.total.goodneighbor.service.MineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/mine")
public class ChatController extends ServiceImpl<UserMapper, userinformation> {
    @Autowired
    private MineService mineService;

    @PostMapping


    @GetMapping("/sendavatar")
    public ResponseEntity<String> setavater(@RequestBody String email) {
        return null;
    }
}
