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

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {
    private Button btn_back;
    private LinearLayout tv_window1;
    private LinearLayout tv_window2;
    private LinearLayout tv_window3;
    private LinearLayout tv_window4;
    List<ImageView> images=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_main, null);
        View view2=inflater.inflate(R.layout.main_posting1,null);

        //图片滚动效果
//        ImageView imageView=view.findViewById(R.id.tv_home_viewPager);
//        imageView.setImageResource(R.drawable.activity_1);
//
//        ImageView imageView2=view.findViewById(R.id.tv_home_viewPager);
//        imageView2.setImageResource(R.drawable.home_check);

        //适配器
//        ImagePagerAdapter adapter=new ImagePagerAdapter(this,images);

//        images.add(imageView);
//        images.add(imageView2);

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
