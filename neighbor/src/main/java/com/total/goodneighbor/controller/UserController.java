package com.total.goodneighbor.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.total.goodneighbor.entity.userinformation;
import com.total.goodneighbor.mapper.UserMapper;
import com.total.goodneighbor.service.IntegralService;
import com.total.goodneighbor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController extends ServiceImpl<UserMapper, userinformation>{
    @Autowired
    private UserService loginService;
    @Autowired
    private IntegralService integralService;

    @PostMapping("/login")
    public ResponseEntity<String> loginRequest(@RequestBody String email) {
        loginService.saveemail(email);
        System.out.println(email);
        return new ResponseEntity<>("接受邮箱成功", HttpStatus.OK);
    }
    @PostMapping("/real")
    @ResponseBody
    public userinformation login(String email,String realname, String id){
        userinformation user = this.loginService.saverealname(email,realname, id);
        System.out.println(user.toString());
        return user;
    }

    @PostMapping("/message")
    @ResponseBody
    public userinformation message(String email,String uri,String nickname,String sex,String phone,String buildingnumber,String address){
        userinformation user=this.loginService.savemessage(email,uri,nickname,sex,phone,buildingnumber,address);
        return user;
    }

    @PostMapping("/getintegral")
    @ResponseBody
    public int getintegral(String email){
        System.out.println(loginService.integral(email));
        return loginService.integral(email);
    }

    @PostMapping("/rank")
    @ResponseBody
    public List<Object> rank(String email){
        return integralService.rank(email);
    }
}
