package com.example.goodneighbor.Activity.Mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.goodneighbor.Activity.Login.LoginActivity;
import com.example.goodneighbor.R;
import com.example.goodneighbor.database.PrefManager;

public class MineFragment extends Fragment implements View.OnClickListener {

    private Button btn_Nickname;
    private Button btn_About, btn_exit;
    private Button btn_message;
    private  Button btn_share_wite;
    private ImageButton ib_imageAvatar;
    private TextView tv_integral;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_mine, null);
        SharedPreferences shared= getActivity().getSharedPreferences("share", Context.MODE_PRIVATE);
        //获取头像
        ib_imageAvatar = view.findViewById(R.id.ib_imageAvatar);
        Bundle bundle=getActivity().getIntent().getExtras();
        String uri = bundle.getString("uri");
        String nickname=bundle.getString("nickname");
        //获取积分
        tv_integral=view.findViewById(R.id.tv_integral);
        tv_integral.setText(shared.getInt("integral",0)+"积分");
        ib_imageAvatar.setImageURI(Uri.parse(uri));
        //获取昵称
        btn_Nickname = view.findViewById(R.id.btn_Nickname);
        String name="快来给自己取一个好听的名字吧！";
        if(nickname!=name)
        btn_Nickname.setText(nickname);
        btn_message = view.findViewById(R.id.btn_message);
        btn_About = view.findViewById(R.id.btn_About);
        btn_exit = view.findViewById(R.id.btn_exit);
        btn_share_wite=view.findViewById(R.id.btn_share_wite);
        btn_Nickname.setText(shared.getString("email",""));
        ib_imageAvatar.setOnClickListener(this);
        btn_Nickname.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_message.setOnClickListener(this);
        btn_About.setOnClickListener(this);
        btn_share_wite.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        PrefManager prefManager = new PrefManager(getActivity());
        if (v.getId() == R.id.btn_Nickname) {
            startActivity(new Intent(getActivity(), EditMessageActivity.class));
        } else if (v.getId() == R.id.btn_About) {
            startActivity(new Intent(getActivity(), AboutActivity.class));
        } else if (v.getId() == R.id.btn_exit) {
            prefManager=new PrefManager(getActivity());
            prefManager.setFirstTimeLaunch(true);
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else if (v.getId() == R.id.btn_message) {
            startActivity(new Intent(getActivity(), EditMessageActivity.class));
        } else if (v.getId() == R.id.ib_imageAvatar) {
            startActivity(new Intent(getActivity() , EditMessageActivity.class));
        }else if(v.getId()==R.id.btn_share_wite){
            startActivity(new Intent(getActivity() , ReadyShare.class));
        }
    }
}



    //底部四个按钮
    //private Button btn_circle,btn_main,btn_share,btn_mine;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mine_mine);
//        btn_Nickname=findViewById(R.id.btn_Nickname);
//        btn_Nickname.setOnClickListener(this);
//        btn_About=findViewById(R.id.btn_About);
//        btn_About.setOnClickListener(this);
//        btn_exit=findViewById(R.id.btn_exit);
//        btn_exit.setOnClickListener(this);
//        ib_imageAvatar=findViewById(R.id.ib_imageAvatar);
//        ib_imageAvatar.setOnClickListener(this);
//        //btn_Nickname.setText(userInfo.email);
//        btn_message=findViewById(R.id.btn_message);
//        btn_message.setOnClickListener(this);
//        mHelper = UserDBHelper.getInstance(this, 1);
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
//}
///*    private void updatePointsText() {
//        textPoints.setText("积分：" + points);
//    }*/

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.btn_Nickname) {
//            startActivity(new Intent(this,EditMessageActivity.class));
//        } else if (v.getId()==R.id.btn_About) {
//            startActivity(new Intent(this,AboutActivity.class));
//        } else if (v.getId()==R.id.btn_exit) {
//            startActivity(new Intent(this,LoginActivity.class));
//        }else if(v.getId()==R.id.btn_message){
//            startActivity(new Intent(this,EditMessageActivity.class));
//        }else if(v.getId()==R.id.ib_imageAvatar){
//            startActivity(new Intent(this,EditMessageActivity.class));
//        }
//  /*      else if(v.getId()==R.id.btn_About){startActivity(new Intent(this,AboutActivity.class));}
//        else if(v.getId()==R.id.btn_main){startActivity(new Intent(this,MainActivity.class));}
//        else if(v.getId()==R.id.btn_share){startActivity(new Intent(this,ShareActivity.class));}
//        else if(v.getId()==R.id.btn_circle){startActivity(new Intent(this,CircleActivity.class));}
//        else if(v.getId()==R.id.btn_mine){startActivity(new Intent(this,MineActivity.class));}*/
//    }

