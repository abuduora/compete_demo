package com.example.goodneighbor.Activity.Mine;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.Activity.Share.Capture;
import com.example.goodneighbor.R;
import com.example.goodneighbor.database.PrefManager;
import com.example.goodneighbor.util.OkHttp;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MineGoods1 extends AppCompatActivity {
    private Button share_sharing2;

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // 扫描被取消
                Toast.makeText(this, "扫描取消", Toast.LENGTH_SHORT).show();
            }else {
                // 扫描成功，result.getContents() 包含了扫描的内容
                String scannedData = result.getContents();
                String address = scannedData.substring(0, scannedData.indexOf(" "));
                Pattern number= Pattern.compile("\\d+");
                Matcher m = number.matcher(scannedData);
                m.find();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final Object lock = new Object();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //   synchronized (lock) {
                        SharedPreferences shared = getSharedPreferences("sahre", MODE_PRIVATE);
                        RequestBody requestBody = new FormBody.Builder()
                                .add("email", "1574860363@qq.com")
                                .add("tname", "switch")
                                .add("box_id", m.group())
                                .add("community", "幸福社区").build();
                        OkHttp.Post("http://[240e:404:b830:a118:61ce:6331:f25f:c199]:9776/share/shenhe", requestBody, new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                request = response.body().string();
                                Log.d("发送请求的到报文", request);
                                runOnUiThread(() -> {
                                    System.out.println(request);
                                    String a = new String();
                                    a = "您的审核已通过，是否继续进行共享";
                                    boolean isEqual = a.equals(request);
                                    if (isEqual) {
                                        builder.setTitle("成功!");
                                        builder.setMessage(request);
                                        builder.setPositiveButton("继续共享", (dialog, which) -> {
                                            RequestBody requestBody = new FormBody.Builder()
                                                    .add("email", "1574860363@qq.com")
                                                    .add("tname", "switch")
                                                    .add("box_id", "1")
                                                    .build();
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    OkHttp.Post("http://[240e:404:b830:a118:61ce:6331:f25f:c199]:9776/share/opendoor", requestBody, new Callback() {
                                                        @Override
                                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                                        }

                                                        @Override
                                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                            resp = response.body().string();
                                                            runOnUiThread(()->{
                                                                String s = new String();
                                                                s = "图片相似度比对通过，请关紧箱门";
                                                                boolean areEqual = s.equals(resp);
                                                                if (areEqual) {
                                                                    builder.setTitle("成功!");
                                                                    builder.setMessage(resp);
                                                                    builder.setPositiveButton("确定返回", (dialog, which) -> {
                                                                        finish();
                                                                    });
                                                                    builder.setNegativeButton("我再看看", null);
                                                                    AlertDialog alert = builder.create();
                                                                    alert.show();
                                                                } else {
                                                                    builder.setTitle("失败!");
                                                                    builder.setMessage(resp);
                                                                    builder.setPositiveButton("确定返回", (dialog, which) -> {
                                                                        finish();
                                                                    });
                                                                    builder.setNegativeButton("我再看看", null);
                                                                    AlertDialog alert = builder.create();
                                                                    alert.show();
                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            }).start();
                                        });
                                        builder.setNegativeButton("我再看看", ((dialog, which) -> {
                                            finish();
                                        }));
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
                                });
                            }
                        });
                        /*Log.d("发送请求之后",request);
                        if (request != null)
                            lock.notify();*/
                        // }

                    }
                }).start();
            /*synchronized (lock) {
                try {
                    lock.wait();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                //    }
            }
    }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public String request,resp;
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_switch);
        PrefManager prefManager = new PrefManager(this);
        share_sharing2=findViewById(R.id.share_switch);

         share_sharing2.setOnClickListener(v -> {
             //Initialize intent integrator
             IntentIntegrator intentIntegrator=new IntentIntegrator(MineGoods1.this);
             //Set prompt text
             intentIntegrator.setPrompt("点击音量+键打开闪光灯");
             //Set beep
             intentIntegrator.setBeepEnabled(true);
             //Locked orientation
             intentIntegrator.setOrientationLocked(true);
             //Set capture activity
             intentIntegrator.setCaptureActivity(Capture.class);
             //Initiate scan
             intentIntegrator.initiateScan();
         });
    }

    //实现二维码数据提取
    private void cheek(String QR_String){
        String address = QR_String.substring(0, QR_String.indexOf(" "));
        Pattern number= Pattern.compile("\\d+");
        Matcher m = number.matcher(QR_String);
        m.find();
        System.out.println("截取 之前字符串:"+address);
        System.out.println("提取的数字："+m.group());
    }
}
