package com.example.goodneighbor.Activity.Mine;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.goodneighbor.Activity.Login.LoginActivity;
import com.example.goodneighbor.R;
import com.example.goodneighbor.bean.UserInfo;
import com.example.goodneighbor.database.UserDBHelper;

public class MineActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_Nickname;
    private Button btn_About,btn_exit;
    UserInfo userInfo = new UserInfo();
    private UserDBHelper mHelper;
    private Button btn_message;
    private ImageButton ib_imageAvatar;
    //底部四个按钮
    //private Button btn_circle,btn_main,btn_share,btn_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_mine);
        btn_Nickname=findViewById(R.id.btn_Nickname);
        btn_Nickname.setOnClickListener(this);
        btn_About=findViewById(R.id.btn_About);
        btn_About.setOnClickListener(this);
        btn_exit=findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);
        ib_imageAvatar=findViewById(R.id.ib_imageAvatar);
        ib_imageAvatar.setOnClickListener(this);
        btn_Nickname.setText(userInfo.email);
        btn_message=findViewById(R.id.btn_message);
        btn_message.setOnClickListener(this);
        mHelper = UserDBHelper.getInstance(this, 1);
        /*//底部四个选择按钮的监听器
        btn_main=findViewById(R.id.btn_main);
        btn_main.setOnClickListener(this);
        btn_share=findViewById(R.id.btn_share);
        btn_share.setOnClickListener(this);
        btn_circle=findViewById(R.id.btn_circle);
        btn_circle.setOnClickListener(this);
        btn_mine=findViewById(R.id.btn_mine);
        btn_mine.setOnClickListener(this);*/

        // 设置登录按钮的点击事件
        /*btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理登录逻辑
                // 这里简单地假设登录成功，并更新欢迎文本和积分
                //textWelcome.setText("欢迎，用户！");
                points = 100; // 假设用户登录后获得100积分
                updatePointsText();
            }
        });
*/
    }
 /* private void updatePointsText() {
        textPoints.setText("积分：" + points);
    }*/

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_Nickname) {
            startActivity(new Intent(this,EditMessageActivity.class));
        } else if (v.getId()==R.id.btn_About) {
            startActivity(new Intent(this,AboutActivity.class));
        } else if (v.getId()==R.id.btn_exit) {
            startActivity(new Intent(this,LoginActivity.class));
        }else if(v.getId()==R.id.btn_message){
            startActivity(new Intent(this,EditMessageActivity.class));
        }else if(v.getId()==R.id.ib_imageAvatar){
            startActivity(new Intent(this,EditMessageActivity.class));
        }
  /*      else if(v.getId()==R.id.btn_About){startActivity(new Intent(this,AboutActivity.class));}
        else if(v.getId()==R.id.btn_main){startActivity(new Intent(this,MainActivity.class));}
        else if(v.getId()==R.id.btn_share){startActivity(new Intent(this,ShareActivity.class));}
        else if(v.getId()==R.id.btn_circle){startActivity(new Intent(this,CircleActivity.class));}
        else if(v.getId()==R.id.btn_mine){startActivity(new Intent(this,MineActivity.class));}*/
    }
}

