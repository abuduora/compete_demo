package com.example.goodneighbor.Activity.Mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.goodneighbor.R;

public class AboutActivity extends AppCompatActivity {
    private Button btn_About;
    private View button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_about);
      /*  btn_About=findViewById(R.id.btn_About);*/
//跳转到我的
        button = findViewById(R.id.btn_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(AboutActivity.this, MineFragment.class);
                startActivity(intent);
            }
        });
    }
}