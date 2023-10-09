package com.example.goodneighbor.Activity.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.Activity.Main.PageActivity;
import com.example.goodneighbor.R;
import com.example.goodneighbor.database.PrefManager;
import com.example.goodneighbor.util.HttpUtil;

import java.util.HashMap;

public class RealnameActivity extends AppCompatActivity {
    private Button btn_authentication;
    private EditText et_realname;
    private EditText et_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_realname);
        btn_authentication=findViewById(R.id.btn_authentication);
        et_realname=findViewById(R.id.et_realname);
        et_ID=findViewById(R.id.et_ID);
        PrefManager prefManager = new PrefManager(this);
        btn_authentication.setOnClickListener(v -> {
            Intent intent = new Intent(this, PageActivity.class);
        String id = et_ID.getText().toString();
            if (id.length() != 18) {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;}
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            new Thread(new Runnable(){
                public void run(){
                    HttpUtil.post("http://172.20.10.2:9776/user/real",et_realname.getText().toString(),new HashMap<>());
                    HttpUtil.post("http://172.20.10.2:9776/user/real",et_ID.getText().toString(),new HashMap<>());
                }
            }).start();
            prefManager.setFirstRealname(false);
        });
    }
}