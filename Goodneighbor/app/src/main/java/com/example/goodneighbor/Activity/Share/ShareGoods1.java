package com.example.goodneighbor.Activity.Share;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class ShareGoods1 extends AppCompatActivity {
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // 扫描被取消
                Toast.makeText(this, "扫描取消", Toast.LENGTH_SHORT).show();
            } else {
                // 扫描成功，result.getContents() 包含了扫描的内容
                String scannedData = result.getContents();
                String address = scannedData.substring(0, scannedData.indexOf(" "));
                Pattern number= Pattern.compile("\\d+");
                Matcher m = number.matcher(scannedData);
                m.find();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                SharedPreferences shared=this.getSharedPreferences("share", Context.MODE_PRIVATE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequestBody requestBody=new FormBody.Builder()
                                .add("box_id",m.group())
                                .build();
                        OkHttp.Post("http://[240e:404:b830:a118:61ce:6331:f25f:c199]:9776/share/justopendoor", requestBody, new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                Toast.makeText(ShareGoods1.this, "共享失败，请检查网络设置", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                request=response.body().string();
                            }
                        });
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

                System.out.println("截取 之前字符串:"+address);
                System.out.println("提取的数字："+m.group());
                Toast.makeText(this, "扫描结果: " + scannedData, Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public String request;
    private Button btScan;
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_goods3);
        PrefManager prefManager = new PrefManager(this);
        Button btn1=findViewById(R.id.share_sharing1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize intent integrator
                IntentIntegrator intentIntegrator=new IntentIntegrator(ShareGoods1.this);
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
            }
        });
    }



/*
 @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Initialize intent result
        IntentResult intentResult=IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data);
        //Check condition
        if(intentResult.getContents()!=null)
        {
            //When result content is not null
            //Initialize alert dialog
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(ShareGoods1.this);
            //Set title
            builder.setTitle("Result");
            //Set message
            builder.setMessage(intentResult.getContents());
            //Set positive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Dismiss dialog
                    dialogInterface.dismiss();
                }
            });
            //Show alert dialog
            builder.show();
        }else{
            //When result content is null
            //Display toast
            Toast.makeText(getApplicationContext()
                    ,"OOPS...You did not scan anything",Toast.LENGTH_SHORT
            ).show();
        }
    }
*/
}
