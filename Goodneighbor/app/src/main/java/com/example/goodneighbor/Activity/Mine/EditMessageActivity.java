package com.example.goodneighbor.Activity.Mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;
import com.example.goodneighbor.util.HttpUtil;

import java.util.HashMap;

public class EditMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_back,btn_finish;
    private ImageView iv_image;
    private ActivityResultLauncher<Intent> mRresultLauncher;
    private  Uri picUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_message);
        iv_image = findViewById(R.id.iv_image);
        iv_image.setOnClickListener(this);
        //跳转到系统相册，选择图片，并返回
        mRresultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK){
                    Intent intent=result.getData();
                   picUri=intent.getData();
                    if(picUri !=null){
                       iv_image.setImageURI(picUri);
                        Log.d("image","picUri"+picUri.toString());
                    }
                }
            }
        });

        //跳转到我的
       btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
       btn_finish = findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       //HttpUtil.post("http://172.20.10.2:9776/mine/",picUri,new HashMap<>());
                       HttpUtil.getImage("http://[240e:404:b701:8df3:3ec0:d27f:3969:5455]:9776/mine/avatar",new HashMap<>());
                    }
                }).start();
                finish();
            }
        });
    }
    //添加头像
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_image){
            Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            mRresultLauncher.launch(intent);
    }
}
}