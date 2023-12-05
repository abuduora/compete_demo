package com.example.goodneighbor.Activity.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;

import java.io.File;

public class MainPosating extends AppCompatActivity {

    //    要展示的对应item的数据，imgs是上方的图片/视图
    //    titles是标题，headsIcon是头像，username是用户名
    private File[] imgs;
    private int[] imgs_posting={R.drawable.heead,R.drawable.share2,R.drawable.share4,R.drawable.share5,R.drawable.share5,R.drawable.share1,R.drawable.share2,R.drawable.share4,R.drawable.share5,R.drawable.share5};
    private String[] titles={"我昨天打代码打久了，现在肩膀太酸了，有没有人能帮帮我啊","谁能帮我孩子啊？下午4点，孩子学校私聊！！，借来救救急","帅猫","夕阳下的教学楼","看不见的","电影看书的女人","阿呆的沙雕绘画","帅猫","夕阳下的教学楼","看不见的"};
    private int[] headsIcon={R.drawable.user_d,R.drawable.user2,R.drawable.user3,R.drawable.user4,R.drawable.user4,R.drawable.user1,R.drawable.user2,R.drawable.user3,R.drawable.user4,R.drawable.user4};
    private String[] usernames={"热心居民","Wacke","肉团","加密","ajia","阿呆","Wacke","肉团","加密","ajia"};
    private TextView get_posting;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_posting1);

        ImageView posating_head=findViewById(R.id.posating_head);
        TextView posating_userName=findViewById(R.id.posating_userName);
        TextView posating_title=findViewById(R.id.posating_title);
        ImageView posating_userImg=findViewById(R.id.posating_userImg);

        SharedPreferences shared=getSharedPreferences("main",MODE_PRIVATE);
        int position_main=shared.getInt("position_main",0);

        get_posting=findViewById(R.id.get_posting);
        posating_head.setImageResource(headsIcon[position_main]);
        posating_userName.setText(usernames[position_main]);
        posating_title.setText(titles[position_main]);
        posating_userImg.setImageResource(imgs_posting[position_main]);

        get_posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainPosating.this,MainPostingQR.class);
                startActivity(i);
            }
        });
    }
}
