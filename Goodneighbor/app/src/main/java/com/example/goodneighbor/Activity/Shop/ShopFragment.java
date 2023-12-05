package com.example.goodneighbor.Activity.Shop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.goodneighbor.R;
import com.example.goodneighbor.bean.MainApplication;
import com.example.goodneighbor.database.GoodsInfo;
import com.example.goodneighbor.database.ShoppingDBHelper;
import com.example.goodneighbor.util.ToastUtil;

import java.util.List;
public class ShopFragment extends Fragment{
    // 声明一个商品数据库的帮助器对象
    private ShoppingDBHelper mDBHelper;
    private TextView tv_count;
    private GridLayout gl_channel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shopping_channel,null);
         //TextView tv_title = view.findViewById(R.id.tv_title);

        tv_count = view.findViewById(R.id.tv_count);
        gl_channel = view.findViewById(R.id.gl_channel);
        view.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击了返回图标，关闭当前页面
                getActivity().finish();
            }
        });
        view.findViewById(R.id.iv_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击了购物车图标
                // 从商场页面跳到购物车页面
                Intent intent = new Intent(getActivity(), ShoppingCartActivity.class);
                // 设置启动标志，避免多次返回同一页面的
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        mDBHelper = ShoppingDBHelper.getInstance(getActivity());
        Log.i("1","1");
        mDBHelper.openReadLink();
        mDBHelper.openWriteLink();

        // 从数据库查询出商品信息，并展示
        showGoods();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 查询购物车商品总数，并展示
        showCartInfoTotal();
    }

    // 查询购物车商品总数，并展示
    private void showCartInfoTotal() {
      System.out.println(mDBHelper);
        int count = mDBHelper.countCartInfo();
        MainApplication.getInstance().goodsCount = count;
        tv_count.setText(String.valueOf(count));
    }

    private void showGoods() {
        // 商品条目是一个线性布局，设置布局的宽度为屏幕的一半
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth / 2, LinearLayout.LayoutParams.WRAP_CONTENT);
        // 查询商品数据库中的所有商品记录
        List<GoodsInfo> list = mDBHelper.queryAllGoodsInfo();

        // 移除下面的所有子视图
        gl_channel.removeAllViews();

        for (GoodsInfo info : list) {
            // 获取布局文件item_goods.xml的根视图
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_goods, null);
            ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_price = view.findViewById(R.id.tv_price);
            Button btn_add = view.findViewById(R.id.btn_add);

            // 给控件设置值
            iv_thumb.setImageURI(Uri.parse(info.picPath));
            tv_name.setText(info.name);
            tv_price.setText(String.valueOf((int) info.price));

            // 添加到购物车
            btn_add.setOnClickListener(v -> {
                addToCart(info.id, info.name);
            });

            // 点击商品图片，跳转到商品详情页面
            iv_thumb.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ShoppingDetailActivity.class);
                intent.putExtra("goods_id", info.id);
                startActivity(intent);
            });

            // 把商品视图添加到网格布局
            gl_channel.addView(view, params);
        }
    }

    // 把指定编号的商品添加到购物车
    private void addToCart(int goodsId, String goodsName) {
        // 购物车商品数量+1
        int count = ++MainApplication.getInstance().goodsCount;
        tv_count.setText(String.valueOf(count));
        mDBHelper.insertCartInfo(goodsId);
        ToastUtil.show(getActivity(), "已添加" + goodsName + "到购物车");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDBHelper.closeLink();
    }
}