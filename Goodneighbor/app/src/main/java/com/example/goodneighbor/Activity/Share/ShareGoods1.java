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

public class ShareGoods1 extends AppCompatActivity {
    public String request;
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_goods1);
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
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    request = HttpUtil.share("http://[240e:404:b701:8df3:3ec0:d27f:3969:5455]:9776/share/opendoor", "开门", new HashMap<>());
                    if (request != null)
                        lock.notify();
                }
            }
        }).start();
        synchronized (lock) {
                    try {
                        lock.wait();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(request);
                    String s = new String();
                    s = "图片相似度比对通过，请关紧箱门";
                    boolean areEqual = s.equals(request);
                    if (areEqual) {
                        builder.setTitle("成功!");
                        builder.setMessage(request);
                        builder.setPositiveButton("确定返回", (dialog, which) -> {
                          finish();
                        });
                        builder.setNegativeButton("我再看看", null);
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        builder.setTitle("失败!");
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
    }
}
