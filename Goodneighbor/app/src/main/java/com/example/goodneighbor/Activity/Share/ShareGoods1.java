package com.example.goodneighbor.Activity.Share;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;
import com.example.goodneighbor.database.PrefManager;
import com.example.goodneighbor.util.HttpUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class ShareGoods1 extends AppCompatActivity {
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
                intentIntegrator.setPrompt("For flash ues volume up key");
                //Set beep
                intentIntegrator.setBeepEnabled(true);
                //Locked orientation
                intentIntegrator.setOrientationLocked(true);
                //Set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);
                //Initiate scan
                intentIntegrator.initiateScan();
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

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // 扫描被取消
                Toast.makeText(this, "扫描取消", Toast.LENGTH_SHORT).show();
            } else {
                // 扫描成功，result.getContents() 包含了扫描的内容
                String scannedData = result.getContents();

                Toast.makeText(this, "扫描结果: " + scannedData, Toast.LENGTH_SHORT).show();
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

   /* @Override
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
    }*/

        }