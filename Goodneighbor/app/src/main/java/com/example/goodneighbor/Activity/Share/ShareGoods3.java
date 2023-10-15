package com.example.goodneighbor.Activity.Share;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;
import com.example.goodneighbor.database.PrefManager;
import com.example.goodneighbor.util.HttpUtil;

import java.util.HashMap;

public class ShareGoods3 extends AppCompatActivity {
    public String request;
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_goods3);
        PrefManager prefManager = new PrefManager(this);
        Button btn1=findViewById(R.id.share_sharing1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder();
            }
        });
    }
    public void builder(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                    request = HttpUtil.share("http://[240e:404:b701:8df3:3ec0:d27f:3969:5455]:9776/share/justopendoor", "开门", new HashMap<>());
            }
        }).start();
                builder.setTitle("请取出物品，关好箱门!");
                builder.setMessage(request);
                builder.setPositiveButton("确定返回", (dialog, which) -> {
                    finish();
                });
                builder.setNegativeButton("我再看看", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        }




        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(request=="图片相似度比对通过，请关紧箱门") {
            builder.setTitle("成功！");
            builder.setMessage(request);
            builder.setPositiveButton("确定返回", (dialog, which) -> {
                AlertDialog alert = builder.create();
                alert.show();
            });
        }else{
            builder.setTitle("错误！");
            builder.setMessage(request);
            builder.setPositiveButton("确定返回", (dialog, which) -> {
                AlertDialog alert = builder.create();
                alert.show();
            });
        }*/
