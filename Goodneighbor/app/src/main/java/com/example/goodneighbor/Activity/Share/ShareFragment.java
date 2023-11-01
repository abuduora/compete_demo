package com.example.goodneighbor.Activity.Share;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.goodneighbor.R;
import com.example.goodneighbor.bean.ArticleAdapter;
import com.example.goodneighbor.bean.ArticleInfo;
import com.example.goodneighbor.bean.FloatingButtonService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShareFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private String urlStr;//服务器对应的URL
    //下拉刷新
    private  SwipeRefreshLayout swipeRefreshLayout;// 声明一个下拉刷新布局对象

    private final static String TAG = "PullRefreshActivity";
    private SwipeRefreshLayout srl_dynamic;//声明下拉对象
    private RecyclerView rv_dynamic; // 声明一个循环视图对象
    private RecyclerView ll_bottom; // 声明一个线性视图对象
    private ArticleAdapter mAdapter; // 声明一个线性适配器对象
    private int mPageNo = 0; // 已加载的分页序号
    private List<ArticleInfo> mArticleList = new ArrayList<>(); // 文章列表
    private int mLastVisibleItem = 0; // 最后一个可见项的序号
    private Handler mHandler = new Handler(Looper.myLooper()); // 声明一个处理器对象

    private static final int REQUEST_CODE = 1;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.share_share,null);
ImageView share_publish=view.findViewById(R.id.share_publish);
        share_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),SharePublish.class);
            }
        });

        /*悬浮按钮*/
        super.onCreate(savedInstanceState);

        // 检查是否有悬浮窗口权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(getActivity())) {
            // 如果没有权限，请求用户授权
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getActivity().getPackageName()));
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            // 如果有权限，启动悬浮按钮的服务
            startFloatingButtonService();
        }

        // 设置下拉刷新布局的进度圆圈颜色
        srl_dynamic=view.findViewById(R.id.swipeRefreshLayout);
        srl_dynamic.setColorSchemeResources(
                R.color.red, R.color.orange, R.color.green, R.color.blue_1);
        srl_dynamic.setOnRefreshListener(this); // 设置下拉布局的下拉刷新监听器
        initRecyclerDynamic(); // 初始化动态线性布局的循环视图
        ll_bottom = view.findViewById(R.id.home_item);//分配刷新头
        mHandler.post(() -> onRefresh()); // 刚打开页面时候的初始加载

        RecyclerView recyclerView1=view.findViewById(R.id.home_item);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(),2));//设置为表格布局，列数为2（这个是最主要的，就是这个来展示陈列式布局）
        recyclerView1.addItemDecoration(new space_item(5));//给recycleView添加item的间距

        return view;
    }

    // 初始化动态线性布局的循环视图
    private void initRecyclerDynamic() {
        rv_dynamic = view.findViewById(R.id.home_item);
        // 创建一个垂直方向的线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rv_dynamic.setLayoutManager(manager); // 设置循环视图的布局管理器
        // 构建一个文章列表的线性适配器
        mAdapter = new ArticleAdapter(getActivity(), mArticleList);
        rv_dynamic.setAdapter(mAdapter);  // 设置循环视图的线性适配器
        rv_dynamic.setItemAnimator(new DefaultItemAnimator());  // 设置循环视图的动画效果
        // 给循环视图添加滚动监听器
        rv_dynamic.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) { // 滚动停止
                    // 滚到了最后一项
                    if (mLastVisibleItem+1 == mAdapter.getItemCount()) {
                        // 显示底部的加载更多文字
                        ll_bottom.setVisibility(View.VISIBLE);
                        // 滚到最后一项
                        rv_dynamic.scrollToPosition(mArticleList.size()-1);
                        // 延迟500毫秒后加载更多文章
                        mHandler.postDelayed(() -> loadArticle(), 500);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 寻找最后一个可见项的序号
                mLastVisibleItem = manager.findLastVisibleItemPosition();
            }
        });
    }

    // 一旦在下拉刷新布局内部往下拉动页面，就触发下拉监听器的onRefresh方法
    @Override
    public void onRefresh() {
        mArticleList.clear();
        mPageNo = 0;
        loadArticle();

        // 加载页面
        loadArticle();
    }

    // 加载页面可以从服务器获取json数据
    private void loadArticle() {
        try {
            URL url=new URL(urlStr);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            InputStream input=connection.getInputStream();
            connection.setRequestMethod("GET");

            BufferedReader in=new BufferedReader(new InputStreamReader(input));
            StringBuilder response =new StringBuilder();
            String line=null;

            while ((line =in.readLine())!=null){
                response.append(line);
            }
            //关闭
            in.close();
            connection.disconnect();

            //解析JSON
            JSONObject jsonObject=new JSONObject(response.toString());
            showArticle(jsonObject.toString());

        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    // 显示返回的文章
    private void showArticle(String resp) {
        srl_dynamic.setRefreshing(false);
        int lastSize = mArticleList.size();
        List<ArticleInfo> addList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(resp);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray datas = data.getJSONArray("datas");
            for (int i=0; i<datas.length(); i++) {
                JSONObject userObject  = datas.getJSONObject(i);
                ArticleInfo article = new ArticleInfo();
                article.setTitle(userObject .getString("title"));
                addList.add(article);
            }
            mArticleList.addAll(addList);
            if (lastSize == 0) { // 下拉刷新开头文章
                mAdapter.notifyDataSetChanged(); // 刷新所有列表项数据
            } else { // 上拉加载更多文章
                // 只刷新指定范围的列表项数据
                mAdapter.notifyItemRangeInserted(lastSize, addList.size());
            }
            ll_bottom.setVisibility(View.GONE); // 隐藏底部的加载更多文字
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class space_item extends RecyclerView.ItemDecoration{
        //设置item的间距
        private int space=5;
        public space_item(int space){
            this.space=space;
        }
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            outRect.bottom=space;
            outRect.top=space;
        }


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 处理用户授权结果
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // 用户已授权，启动悬浮按钮的服务
            startFloatingButtonService();
        }
    }

    private void startFloatingButtonService() {
        // 启动悬浮按钮的服务
        Intent intent = new Intent(getActivity(), FloatingButtonService.class);
        getActivity().startService(intent);
    }
    }