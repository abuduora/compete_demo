package com.example.goodneighbor.Activity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.goodneighbor.R;
import com.example.goodneighbor.bean.ImagePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageActivity extends AppCompatActivity {
    private LinearLayout root;
    private RadioGroup mRadioGroup;
    private ImageView tv_window1;

    ArrayList<ImageView> images = new ArrayList<>();

    private RadioButton tab1,tab2,tab3,tab4;  //四个单选按钮
    private List<View> mViews;   //存放视图
    private int selectItem=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_bottom);

        initView();//初始化数据
        //对单选按钮进行监听，选中、未选中
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int i) {
                if(i==R.id.tv_home)
                {
                    root.removeAllViews();
                    home_init();
                }
                if(i==R.id.tv_share)
                {
                    root.removeAllViews();
                }
                if(i==R.id.tv_message)
                {
                    root.removeAllViews();
                }
                if(i==R.id.tv_me)
                {
                    root.removeAllViews();
                }
            }
        }
        );

    }
    private void initView() {
        //初始化控件
        mRadioGroup=findViewById(R.id.tv_tab);
        tab1=findViewById(R.id.tv_home);
        tab2=findViewById(R.id.tv_share);
        tab3=findViewById(R.id.tv_message);
        tab4=findViewById(R.id.tv_me);
        root=findViewById(R.id.tv_root);


        ImageView imageView=new ImageView(getBaseContext());
        imageView.setImageResource(R.drawable.activity_1);

        ImageView imageView2=new ImageView(getBaseContext());
        imageView2.setImageResource(R.drawable.home_check);

        images.add(imageView);
        images.add(imageView2);
        images.add(new ImageView(getBaseContext()));
        images.add(new ImageView(getBaseContext()));
        images.add(new ImageView(getBaseContext()));

        home_init();
    }

    private void home_init(){
        LinearLayout home_layout=(LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.main_main, null);

        ViewPager viewPager = home_layout.findViewById(R.id.tv_home_viewPager);
        viewPager.setAdapter(new ImagePagerAdapter(this, getBaseContext(), images));

        root.addView(home_layout);

        //图片点击事件
        tv_window1=home_layout.findViewById(R.id.tv_window1);
        tv_window1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tv_window1=new Intent(getBaseContext(),PostingsActivity.class);
                startActivity(tv_window1);
            }
        });
    }
    private void share_init(){

    }

    private void circle(){

    }

    private void mine(){

    }


}