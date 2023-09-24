package com.example.goodneighbor.Activity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.goodneighbor.R;

import java.util.List;


public class MainFragment extends Fragment {
    private Button btn_back;
    private LinearLayout tv_window1;
    private LinearLayout tv_window2;
    private LinearLayout tv_window3;
    private LinearLayout tv_window4;
    private List<Integer> images ;
    private ImageView viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_main, null);
        View view2=inflater.inflate(R.layout.main_posting1,null);

        //图片滚动效果
//        ViewPager2 viewPager=view.findViewById(R.id.viewPager);
//        ViewPagerAdapter viewpagerAdater=new ViewPagerAdapter(images);
//        viewPager.setAdapter(viewpagerAdater);

        viewPager=view.findViewById(R.id.viewPager);
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), MainRanking.class);
                startActivity(i);
            }
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
