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
import androidx.viewpager.widget.ViewPager;

import com.example.goodneighbor.R;
import com.example.goodneighbor.bean.ImagePagerAdapter;
import com.example.goodneighbor.bean.MyBaseAdapter;

import java.util.ArrayList;
import java.util.List;

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
//                    share_init();
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


        //图片滚动效果
        ImageView imageView=new ImageView(getBaseContext());
        imageView.setImageResource(R.drawable.activity_1);

        ImageView imageView2=new ImageView(getBaseContext());
        imageView2.setImageResource(R.drawable.home_check);

        images.add(imageView);
        images.add(imageView2);

        home_init();
        circle_init();
        mine_init();
        share_init();

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


//        //添加求助帖子页面
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
        LinearLayout home_layout=(LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.main_main, null);

//            //初始化ListView控件
//            ListView listView=findViewById(R.id.lv);
//            //创建一个Adapter的实例
//            MyBaseAdapter mAdapter=new MyBaseAdapter();
//            //设置Adapter
//            listView.setAdapter(mAdapter);
//        class MyBaseAdapter extends BaseAdapter{
//
//            @Override
//            public int getCount(){       //得到item的总数
//                return titles.length;
//            }
//
//            @Override
//            public Object getItem(int position){
//                return titles[position]; //返回item的数据对象
//            }
//            @Override
//            public long getItemId(int position){
//                return position;         //返回item的id
//            }
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent){//获取item中的View视图
//                ViewHolder holder;
//                if(convertView==null){
//                    convertView=View.inflate(PageActivity.this,R.layout.share_share, null);
//                    holder=new ViewHolder();
//                    holder.title=convertView.findViewById(R.id.title);
//                    holder.price=convertView.findViewById(R.id.price);
//                    holder.iv=convertView.findViewById(R.id.iv);
//                    convertView.setTag(holder);
//                }else{
//                    holder=(ViewHolder)convertView.getTag();
//                }
//                holder.title.setText(titles[position]);
//                holder.price.setText(prices[position]);
//                holder.iv.setImageResource(icons[position]);
//                return convertView;
//            }
//
//        class ViewHolder{
//            TextView title;
//            TextView price;
//            ImageView iv;
//        }
//        }
    }

    private void circle_init(){
//        LinearLayout circle_layout=(LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.circle_circle, null);
//        root.addView(circle_layout);
    }

     private void mine_init(){
//        LinearLayout mine_layout=(LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.mine_mine, null);
//        root.addView(mine_layout);
    }


}