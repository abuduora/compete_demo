package com.example.goodneighbor.Activity.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.goodneighbor.R;
import com.example.goodneighbor.bean.MainApplication;
import com.example.goodneighbor.bean.ViewPagerAdapter;

import java.util.List;


public class MainFragment extends Fragment {
    private Button btn_back;
    private LinearLayout tv_window1;
    private LinearLayout tv_window2;
    private LinearLayout tv_window3;
    private LinearLayout tv_window4;
    private List<Integer> images ;
    private ViewPager viewPager;
    private ViewPager viewPager2;
    private int[] imageIds=new int[]{R.drawable.main_tatle, R.drawable.notice1,R.drawable.notice2,R.drawable.notice3,R.drawable.arrow};

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_main, null);
        View view2=inflater.inflate(R.layout.main_posting1,null);
        //图片滚动效果
        viewPager=view.findViewById(R.id.main_title_viewPager);
        ViewPagerAdapter adapter=new ViewPagerAdapter(getContext(),imageIds);
        viewPager.setAdapter(adapter);
        SharedPreferences shared= getActivity().getSharedPreferences("share", Context.MODE_PRIVATE);
        MainApplication.getInstance().wechatName =shared.getString("nickname","");


        viewPager.setOnClickListener(v -> {
            Intent i=new Intent(getActivity(), MainRanking.class);
            startActivity(i);
        });

        tv_window1=view.findViewById(R.id.tv_window1);
        btn_back=view2.findViewById(R.id.posting_back1);
        tv_window1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), MainPosating.class);
                startActivity(i);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });
        return view;
    }
}
