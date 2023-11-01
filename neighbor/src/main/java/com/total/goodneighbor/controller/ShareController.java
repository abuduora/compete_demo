package com.total.goodneighbor.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.total.goodneighbor.entity.things;
import com.total.goodneighbor.mapper.ShareMapper;
import com.total.goodneighbor.service.ShareService;
import com.total.goodneighbor.util.HttpUtil;
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
import java.util.HashMap;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/share")
public class ShareController extends ServiceImpl<ShareMapper, things> {
    @Autowired
    private ShareService shareService;

    @PostMapping("/opendoor")
    public String opendoor(@RequestBody String email,String tname,String box_id) {
        String url="http://172.20.10.13:8080/receive_data";
        String image=shareService.getimage(email,tname);
        int status=shareService.getstatus(email,tname);
        if(status==0)
            return "您还未上传要共享商品的信息";
        if (status==3)
            return "该物品正在审核中";
        if(status==2)
            return "该物品未通过审核";
        FormBody requestbody = new FormBody.Builder()
                .add("data",box_id)
                .add("url",image)
                .build();
        OkHttp.Post(url, requestbody, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }

    @PostMapping("/justopendoor")
    public String justopendoor(@RequestBody String email) {
        int i=2;
        String data = Integer.toString(i);
        return HttpUtil.post("http://172.20.10.13:8080/receive_data",data,new HashMap<>());
    }
}
