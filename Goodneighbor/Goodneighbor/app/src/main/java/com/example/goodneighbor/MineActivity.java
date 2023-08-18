package com.example.goodneighbor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MineActivity extends AppCompatActivity {

    private int points = 0;
    private TextView textPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        // 初始化TextView和Button
        TextView textWelcome = findViewById(R.id.btnCommunity);
        textPoints = findViewById(R.id.textPoints);
        Button btnLogin = findViewById(R.id.btnMyPoints);
        Button btnAbout = findViewById(R.id.btnCommunity);

        // 设置登录按钮的点击事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理登录逻辑
                // 这里简单地假设登录成功，并更新欢迎文本和积分
                textWelcome.setText("欢迎，用户！");
                points = 100; // 假设用户登录后获得100积分
                updatePointsText();
            }
        });

        // 设置关于按钮的点击事件
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理关于页面的跳转或弹窗逻辑
            }
        });
    }

    private void updatePointsText() {
        textPoints.setText("积分：" + points);
    }
}
