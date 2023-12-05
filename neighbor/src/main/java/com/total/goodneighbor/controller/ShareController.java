package com.total.goodneighbor.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.total.goodneighbor.entity.things;
import com.total.goodneighbor.mapper.ShareMapper;
import com.total.goodneighbor.service.ShareService;
import com.total.goodneighbor.service.ShareboxService;
import com.total.goodneighbor.util.OkHttp;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/share")
public class ShareController extends ServiceImpl<ShareMapper, things> {
    @Autowired
    private ShareService shareService;
    @Autowired
    private ShareboxService shareboxService;

    @PostMapping("/shenhe")
    @ResponseBody
    public String shenhe( String email,String tname,String box_id,String community) {
        int status=shareService.getstatus(email,tname);
        if(shareboxService.have(community, Integer.parseInt(box_id))){
            System.out.println("该共享柜内已有物品，请更换其他共享柜进行共享");
            return "该共享柜内已有物品，请更换其他共享柜进行共享";
       }
        if(status==0){
            System.out.println("您还未上传要共享商品的信息");
            return "您还未上传要共享商品的信息";}
        if (status==3){
            System.out.println("该物品正在审核中");
            return "该物品正在审核中";}
        if(status==2){
            System.out.println("该物品未通过审核");
            return "该物品未通过审核";}
        else
        {System.out.println("您的审核已通过，是否继续进行共享");
            return "您的审核已通过，是否继续进行共享";}
    }
    @PostMapping("/opendoor")
    @ResponseBody
    public String opendoor( String email,String tname,String box_id){
        String url="http://172.20.10.13:8080/receive_data";
        String image=shareService.getimage(email,tname);
        final String[] resp = new String[1];
        FormBody requestbody = new FormBody.Builder()
            .add("data", box_id)
            .add("url",image)
            .build();
        OkHttp.Post(url, requestbody, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("进入了"+response.body());
                while(response.body()==null){
                    System.out.println("等待");
                }
               resp[0] =response.body().string();
            }
        });
        while (resp[0]==null)
            System.out.println(resp[0]);
        return resp[0];
    }


        @PostMapping("/justopendoor")
    public void justopendoor(String box_id) {
        int i = 0;
        if(Integer.parseInt(box_id)==1)
            i=3;
        if(Integer.parseInt(box_id)==2)
            i=4;
        String data = Integer.toString(i);
        FormBody formBody=new FormBody.Builder()
                .add("data",data)
                        .add("url","0").build();
         OkHttp.Post("http://172.20.10.13:8080/receive_data", formBody, new Callback() {
             @Override
             public void onFailure(@NotNull Call call, @NotNull IOException e) {
             }

             @Override
             public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
             }
         });
    }
}
