package com.example.goodneighbor.Activity.Main;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.goodneighbor.Activity.Share.Capture;
import com.example.goodneighbor.R;
import com.example.goodneighbor.bean.PostingRecycleViewAdapter;
import com.example.goodneighbor.bean.ViewPagerAdapter;
import com.example.goodneighbor.util.OkHttp;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainFragment extends Fragment {
    String resp;
    String request;
    private View view;
    private LinearLayout tv_window1;
    private ViewPager mViewPager;
    private TextView mTvPagerTitle;
    private List<ImageView> mImageList;//轮播的图片集合
    private String[] mImageTitles;//标题集合
    private int previousPosition = 0;//前一个被选中的position
    private List<View> mDots;//小点

    private boolean isStop = false;//线程是否停止
    private static int PAGER_TIOME = 5000;//间隔时间
    private int[] imgae_ids = new int[]{R.id.pager_image1, R.id.pager_image2, R.id.pager_image3, R.id.pager_image4, R.id.pager_image5};

    //    要展示的对应item的数据，imgs是上方的图片/视图
    //    titles是标题，headsIcon是头像，username是用户名
    private int[] imgs={R.drawable.share1,R.drawable.share2,R.drawable.share4,R.drawable.share5,R.drawable.share5,R.drawable.share1,R.drawable.share2,R.drawable.share4,R.drawable.share5,R.drawable.share5};
    private int[] headsIcon={R.drawable.user_d,R.drawable.user2,R.drawable.user3,R.drawable.user4,R.drawable.user4,R.drawable.user1,R.drawable.user2,R.drawable.user3,R.drawable.user4,R.drawable.user4};
    private String[] titles={"我昨天打代码打久了，现在肩膀太酸了，有没有人能帮帮我啊","谁能帮我孩子啊？下午4点，孩子学校私聊！！，借来救救急","谁能帮我看看我家阳台有没有晒衣服","谁能帮我下午去接孩子放学","有没有人在小区看到一只黄色的小猫","谁家有不用的初三教材","谁能帮我照顾一下在家的老人","谁家有多余的伞可以借一下","有没有人帮我照顾小孩","谁家有不用的旧书"};
    private String[] usernames={"热心居民","南桥","肉团","加密","ajia","阿呆","Wacke","肉团","加密","ajia"};
    private TextToSpeech mTTS;
    private String[] user_name={"陈思远","丁若萱"};
    private String[] number={"1878871478","1478956698"};
    private Button  btn_main_QR;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_main, null);

        //图片滚动效果
        TextView tv_publish_ActionButton = view.findViewById(R.id.btn_main_publish);

        init();

        PostingRecycleViewAdapter mAdapter=new PostingRecycleViewAdapter(getContext(),headsIcon,titles,usernames);
        RecyclerView recyclerView=view.findViewById(R.id.main_posting_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(mAdapter);
        btn_main_QR=view.findViewById(R.id.btn_main_QR);
        btn_main_QR.setOnClickListener(v->{
                //Initialize intent integrator
                IntentIntegrator intentIntegrator=new IntentIntegrator(getActivity());
                //Set prompt text
                intentIntegrator.setPrompt("点击音量+键打开闪光灯");
                //Set beep
                intentIntegrator.setBeepEnabled(true);
                //Locked orientation
                intentIntegrator.setOrientationLocked(true);
                //Set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);
                //Initiate scan
                intentIntegrator.initiateScan();
        });
        mAdapter.setOnItemClickListener(new PostingRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position_main) {
                SharedPreferences shared= getActivity().getSharedPreferences("main", MODE_PRIVATE);
                SharedPreferences.Editor editor=shared.edit();
                editor.putInt("position_main",position_main);
                editor.apply();
                Intent i=new Intent(getContext(),MainPosating.class);
                startActivity(i);
            }
        });


        tv_publish_ActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PostingsActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    public void init() {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager_item);
        mTvPagerTitle = (TextView) view.findViewById(R.id.tv_pager_title);
        initData();//初始化数据
        initView();//初始化View，设置适配器
        //autoPlayView();//开启线程，自动播放
    }

    public void initData() {
        //初始化标题列表和图片
        mImageTitles = new String[]{"英雄榜","社区通知","社区通知","社区通知","更多"};
        int[] imageRess = new int[]{R.drawable.main_tatle, R.drawable.notice1, R.drawable.notice2, R.drawable.notice3, R.drawable.arrow};

        //添加图片到图片列表里
        mImageList = new ArrayList<>();
        ImageView iv;
        for (int i = 0; i < imageRess.length; i++) {
            iv = new ImageView(getActivity());
            iv.setBackgroundResource(imageRess[i]);//设置图片
            iv.setId(imgae_ids[i]);//顺便给图片设置id
            iv.setOnClickListener(new pagerImageOnClick());//设置图片点击事件
            mImageList.add(iv);
        }

        //添加轮播点
        LinearLayout linearLayoutDots = (LinearLayout) view.findViewById(R.id.lineLayout_dot);
        mDots = addDots(linearLayoutDots,fromResToDrawable(getActivity(),R.drawable.rolling),mImageList.size());//其中fromResToDrawable()方法是我自定义的，目的是将资源文件转成Drawable
    }

    //图片点击事件
    private class pagerImageOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.pager_image1) {
                Intent i = new Intent(getActivity(),MainRanking.class);
                startActivity(i);
            }
            if (v.getId() == R.id.pager_image2){

            }
            if (v.getId() == R.id.pager_image3){

            }
            if (v.getId() == R.id.pager_image4){

            }
            if (v.getId() == R.id.pager_image5){
                Intent i = new Intent(getActivity(), MainNotice.class);
                startActivity(i);
            }
        }
    }

    /**
     *  第三步、给PagerViw设置适配器，并实现自动轮播功能
     */
    public void initView(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mImageList, mViewPager);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //伪无限循环，滑到最后一张图片又从新进入第一张图片
                int newPosition = position % mImageList.size();
                // 把当前选中的点给切换了, 还有描述信息也切换
                mTvPagerTitle.setText(mImageTitles[newPosition]);//图片下面设置显示文本
                //设置轮播点
                LinearLayout.LayoutParams newDotParams = (LinearLayout.LayoutParams) mDots.get(newPosition).getLayoutParams();
                newDotParams.width = 24;
                newDotParams.height = 24;

                LinearLayout.LayoutParams oldDotParams = (LinearLayout.LayoutParams) mDots.get(previousPosition).getLayoutParams();
                oldDotParams.width = 16;
                oldDotParams.height = 16;

                // 把当前的索引赋值给前一个索引变量, 方便下一次再切换.
                previousPosition = newPosition;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setFirstLocation();
    }

    /**
     * 第四步：设置刚打开app时显示的图片和文字
     */
    private void setFirstLocation() {
        mTvPagerTitle.setText(mImageTitles[previousPosition]);
        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;
        int m = (Integer.MAX_VALUE / 2) % mImageList.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m-1;
        mViewPager.setCurrentItem(currentPosition);
    }

    /**
     * 第五步: 设置自动播放,每隔PAGER_TIOME秒换一张图片
     */
    private void autoPlayView() {
        //自动播放图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                        }
                    });
                    SystemClock.sleep(PAGER_TIOME);
                }
            }
        }).start();
    }



    /**
     * 资源图片转Drawable
     * @param context
     * @param resId
     * @return
     */
    public Drawable fromResToDrawable(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }


    /**
     * 动态添加一个点
     * @param linearLayout 添加到LinearLayout布局
     * @param backgount 设置
     * @return
     */
    public int addDot(final LinearLayout linearLayout, Drawable backgount) {
        final View dot = new View(getActivity());
        LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dotParams.width = 16;
        dotParams.height = 16;
        dotParams.setMargins(4,0,4,0);
        dot.setLayoutParams(dotParams);
        dot.setBackground(backgount);
        dot.setId(View.generateViewId());
        linearLayout.addView(dot);
        return dot.getId();
    }

    /**
     * 添加多个轮播小点到横向线性布局
     * @param linearLayout
     * @param backgount
     * @param number
     * @return
     */
    public List<View> addDots(final LinearLayout linearLayout, Drawable backgount, int number){
        List<View> dots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int dotId = addDot(linearLayout,backgount);
            dots.add(view.findViewById(dotId));
        }
        return dots;
    }
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // 扫描被取消
                Toast.makeText(getActivity(), "扫描取消", Toast.LENGTH_SHORT).show();
            }else {
                // 扫描成功，result.getContents() 包含了扫描的内容
                String scannedData = result.getContents();
                String address = scannedData.substring(0, scannedData.indexOf(" "));
                Pattern number= Pattern.compile("\\d+");
                Matcher m = number.matcher(scannedData);
                m.find();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final Object lock = new Object();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //   synchronized (lock) {
                        SharedPreferences shared = getActivity().getSharedPreferences("sahre", MODE_PRIVATE);
                        RequestBody requestBody = new FormBody.Builder()
                                .add("email", "1574860363@qq.com")
                                .add("tname", "switch")
                                .add("box_id", m.group())
                                .add("community", "幸福社区").build();
                        OkHttp.Post("http://[240e:404:b830:a118:61ce:6331:f25f:c199]:9776/share/shenhe", requestBody, new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                request = response.body().string();
                                Log.d("发送请求的到报文", request);
                                getActivity().runOnUiThread(() -> {
                                    System.out.println(request);
                                    String a = new String();
                                    a = "您的审核已通过，是否继续进行共享";
                                    boolean isEqual = a.equals(request);
                                    if (isEqual) {
                                        builder.setTitle("成功!");
                                        builder.setMessage(request);
                                        builder.setPositiveButton("继续共享", (dialog, which) -> {
                                            RequestBody requestBody = new FormBody.Builder()
                                                    .add("email", "1574860363@qq.com")
                                                    .add("tname", "switch")
                                                    .add("box_id", "1")
                                                    .build();
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    OkHttp.Post("http://[240e:404:b830:a118:61ce:6331:f25f:c199]:9776/share/opendoor", requestBody, new Callback() {
                                                        @Override
                                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                                        }

                                                        @Override
                                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                            resp = response.body().string();
                                                            getActivity().runOnUiThread(()->{
                                                                String s = new String();
                                                                s = "图片相似度比对通过，请关紧箱门";
                                                                boolean areEqual = s.equals(resp);
                                                                if (areEqual) {
                                                                    builder.setTitle("成功!");
                                                                    builder.setMessage(resp);
                                                                    builder.setPositiveButton("确定返回", (dialog, which) -> {
                                                                        getActivity().finish();
                                                                    });
                                                                    builder.setNegativeButton("我再看看", null);
                                                                    AlertDialog alert = builder.create();
                                                                    alert.show();
                                                                } else {
                                                                    builder.setTitle("失败!");
                                                                    builder.setMessage(resp);
                                                                    builder.setPositiveButton("确定返回", (dialog, which) -> {
                                                                        getActivity().finish();
                                                                    });
                                                                    builder.setNegativeButton("我再看看", null);
                                                                    AlertDialog alert = builder.create();
                                                                    alert.show();
                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            }).start();
                                        });
                                        builder.setNegativeButton("我再看看", ((dialog, which) -> {
                                            getActivity().finish();
                                        }));
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    } else {
                                        builder.setTitle("失败!");
                                        builder.setMessage(request);
                                        builder.setPositiveButton("确定返回", (dialog, which) -> {
                                            getActivity().finish();
                                        });
                                        builder.setNegativeButton("我再看看", null);
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    }
                                });
                            }
                        });
                        /*Log.d("发送请求之后",request);
                        if (request != null)
                            lock.notify();*/
                        // }

                    }
                }).start();
            /*synchronized (lock) {
                try {
                    lock.wait();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                //    }
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
