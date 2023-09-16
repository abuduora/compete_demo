package com.example.goodneighbor.Activity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.goodneighbor.R;
import com.example.goodneighbor.bean.ImagePagerAdapter;
import com.example.goodneighbor.bean.MyBaseAdapter;
import com.example.goodneighbor.bean.RecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageActivity extends AppCompatActivity {
    private LinearLayout root;
    private RadioGroup mRadioGroup;
    private LinearLayout tv_window1;
    private TextView btn_postings;
    private String[] titles={"桌子","苹果","蛋糕","线衣","猕猴桃","围巾"};
    private String[] prices={"1800元","10元/kg","300元","350元","10元/kg","280元"};
    private  int[] icons={R.drawable.user1,R.drawable.user2,R.drawable.user3,
            R.drawable.user4,R.drawable.user1,R.drawable.user4};

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
                    share_init();
                }
                if(i==R.id.tv_message)
                {
                    root.removeAllViews();
                    circle_init();
                }
                if(i==R.id.tv_me)
                {
                    root.removeAllViews();
                    mine_init();
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


        //图片滚动效果
        ImageView imageView=new ImageView(getBaseContext());
        imageView.setImageResource(R.drawable.activity_1);

        ImageView imageView2=new ImageView(getBaseContext());
        imageView2.setImageResource(R.drawable.home_check);

        images.add(imageView);
        images.add(imageView2);

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

        //添加求助帖子页面
        btn_postings=home_layout.findViewById(R.id.tv_publish_ActionButton);

        btn_postings.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent btn1=new Intent(getBaseContext(),PostingsActivity.class);
                startActivity(btn1);
            }
        });
    }
    private void share_init(){
        setContentView(R.layout.share_share);
            List<Map<String,String>>mapList=new ArrayList<>();
            Map<String,String>map;
            for(int i=0;i<10;i++)
            {
                map=new HashMap<>();
                map.put("title","商品"+i);
                map.put("price","价格"+i);
                mapList.add(map);
            }
            RecyclerAdapter recyclerAdapter=new RecyclerAdapter(mapList, PageActivity.this);
            RecyclerView recycleView=(RecyclerView) findViewById(R.id.tv_share_recycler);
            recycleView.setLayoutManager(new LinearLayoutManager(this));
            recycleView.setAdapter(recyclerAdapter);
    }

    private void circle_init(){
    LinearLayout circle_layout=(LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.circle_circle, null);
        root.addView(circle_layout);
    }

     private void mine_init(){
        LinearLayout mine_layout=(LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.mine_mine, null);
        root.addView(mine_layout);}
}