package com.example.goodneighbor.Activity.Share;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.goodneighbor.R;

public class ShareFragment extends Fragment {
    @Nullable
    @Override
    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.share_share,null);
//        ListView listView=view.findViewById(R.id.tv_share_recycler);
        TextView window1=view.findViewById(R.id.share_publish);

        //共享物品代码点击
        ImageView share1=view.findViewById(R.id.share_picture1);
        ImageView share2=view.findViewById(R.id.share_picture2);

        window1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), SharePublish.class);
                startActivity(i);
            }
        });

        share1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), ShareGoods1.class);
                startActivity(i);
            }
        });
        share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), ShareGoods2.class);
                startActivity(i);
            }
        });

        return view;
    }
}