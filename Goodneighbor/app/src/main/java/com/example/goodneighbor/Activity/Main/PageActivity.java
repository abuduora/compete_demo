package com.example.goodneighbor.Activity.Main;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.Activity.Circle.CircleFragment;
import com.example.goodneighbor.Activity.Mine.MineFragment;
import com.example.goodneighbor.Activity.Share.ShareFragment;
import com.example.goodneighbor.Activity.Shop.ShopFragment;
import com.example.goodneighbor.R;
import com.example.goodneighbor.bean.MainApplication;

import io.socket.client.Socket;

public class PageActivity extends AppCompatActivity {
    Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_bottom);
        //初始化控件
        RadioGroup mRadioGroup = findViewById(R.id.tv_tab);
        RadioButton tab1 = findViewById(R.id.tv_home);
        RadioButton tab2 = findViewById(R.id.tv_share);
        RadioButton tab3 = findViewById(R.id.tv_message);
        //五个单选按钮
        RadioButton tab4 = findViewById(R.id.tv_me);
        RadioButton tab5 = findViewById(R.id.tv_shop);

        getSupportFragmentManager().beginTransaction().replace(R.id.tv_root,new MainFragment()).commit();

        int id = getIntent().getIntExtra("id", 0);
        if (id == 1) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.tv_root,new MineFragment())
                    .addToBackStack(null)
                    .commit();
        }


        mSocket = MainApplication.getInstance().getSocket();
        mSocket.connect();
        System.out.println("发起连接");

        //初始化数据
        //对单选按钮进行监听，选中、未选中
        mRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            if(i==R.id.tv_home)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.tv_root,new MainFragment()).commit();
            }
            if(i==R.id.tv_share)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.tv_root,new ShareFragment()).commit();
            }
            if(i==R.id.tv_message)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.tv_root,new CircleFragment()).commit();
            }
            if(i==R.id.tv_shop)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.tv_root,new ShopFragment()).commit();
            }
            if(i==R.id.tv_me)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.tv_root,new MineFragment()).commit();
                /*int permission_write= ContextCompat.checkSelfPermission(PageActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permission_write!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "正在请求权限", Toast.LENGTH_SHORT).show();
                    //申请权限，特征码自定义为1，可在回调时进行相关判断
                    ActivityCompat.requestPermissions(PageActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }*/
            }
        }
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //权限已成功申请

                }else{
                    //用户拒绝授权
                    Toast.makeText(this, "无法获取SD卡读写权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
//    private void initView() {
//
////        mSupportFragmentManager = getSupportFragmentManager();
////        mTransaction=mSupportFragmentManager.beginTransaction();
////
////        //默认首先加载主页
////        mRadioGroup.check(R.id.tv_home);
////        homeFragment = new MainFragment();
////        mFragments.add(homeFragment);
////        hideOthersFragment(homeFragment, true);
////
//        //图片滚动效果
//        ImageView imageView=new ImageView(getBaseContext());
//        imageView.setImageResource(R.drawable.activity_1);
//
//        ImageView imageView2=new ImageView(getBaseContext());
//        imageView2.setImageResource(R.drawable.home_check);
//
//        images.add(imageView);
//        images.add(imageView2);
////
//    }
//
//    private void hideOthersFragment(Fragment showFragment,boolean add){
////        mTransaction=mSupportFragmentManager.beginTransaction();
////        if(add){
////            mTransaction.add(R.id.tv_frameLayout,showFragment);
////        }
////        for(Fragment fragment:mFragments){
////            if(showFragment.equals(fragment)){
////                mTransaction.show(fragment);
////            }
////            else mTransaction.hide(fragment);
////        }
////        mTransaction.commit();
//    }
//
//    private void home_init(){
//        LinearLayout home_layout=(LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.main_main, null);
//
//        ViewPager viewPager = home_layout.findViewById(R.id.tv_home_viewPager);
//        viewPager.setAdapter(new ImagePagerAdapter(this, getBaseContext(), images));
//
////        root.addView(home_layout);
//
//        //图片点击事件
//        tv_window1=home_layout.findViewById(R.id.tv_window1);
//        tv_window1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent tv_window1=new Intent(getBaseContext(),PostingsActivity.class);
//                startActivity(tv_window1);
//            }
//        });
//
//        //添加求助帖子页面
//        btn_postings=home_layout.findViewById(R.id.tv_publish_ActionButton);
//
//        btn_postings.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Intent btn1=new Intent(getBaseContext(),PostingsActivity.class);
//                startActivity(btn1);
//            }
//        });
//    }
//    private void share_init(){
//        setContentView(R.layout.share_share);
//            List<Map<String,String>>mapList=new ArrayList<>();
//            Map<String,String>map;
//            for(int i=0;i<10;i++)
//            {
//                map=new HashMap<>();
//                map.put("title","商品"+i);
//                map.put("price","价格"+i);
//                mapList.add(map);
//            }
//            RecyclerAdapter recyclerAdapter=new RecyclerAdapter(mapList, PageActivity.this);
//            RecyclerView recycleView=(RecyclerView) findViewById(R.id.tv_share_recycler);
//            recycleView.setLayoutManager(new LinearLayoutManager(this));
//            recycleView.setAdapter(recyclerAdapter);
//    }
//
//    private void circle_init(){
//        setContentView(R.layout.circle_circle);
//    }
//
//     private void mine_init(){
//        setContentView(R.layout.mine_mine);
//     }
}