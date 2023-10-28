package com.example.goodneighbor.Activity.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.Activity.Main.PageActivity;
import com.example.goodneighbor.R;
import com.example.goodneighbor.database.PrefManager;
import com.example.goodneighbor.util.OkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RealnameActivity extends AppCompatActivity {
    private Button btn_authentication;
    private EditText et_realname;
    private EditText et_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_realname);
        PrefManager prefManager = new PrefManager(this);
        SharedPreferences shared= this.getSharedPreferences("share", Context.MODE_PRIVATE);
        if(prefManager.isFirstRealname()){
        }else{
            Intent intent = new Intent(this, PageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        btn_authentication=findViewById(R.id.btn_authentication);
        et_realname=findViewById(R.id.et_realname);
        et_ID=findViewById(R.id.et_ID);
        btn_authentication.setOnClickListener(v -> {
            Intent intent = new Intent(this, PageActivity.class);
            String realname=et_realname.getText().toString();
        String id = et_ID.getText().toString();
            if (id.length() != 18) {
                Toast.makeText(this, "请输入正确的身份证号", Toast.LENGTH_SHORT).show();
                return;}
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            new Thread(new Runnable(){
                String url="http://[240e:404:2521:9cb7:494f:2883:4f34:1ee]:9776/user/real";
                public void run(){
                    RequestBody requestbody = new FormBody.Builder()
                            .add("email",shared.getString("email",""))
                            .add("realname",realname)
                            .add("id",id)
                            .build();
                    OkHttp.Post(url,requestbody,new Callback(){
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            Looper.prepare();
                            Toast.makeText(RealnameActivity.this, "实名信息成功" , Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Looper.prepare();
                            Toast.makeText(RealnameActivity.this, "实名信息失败，请检查网络设置", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    });
                }
            }).start();
            prefManager.setFirstRealname(false);
        });
    }
}