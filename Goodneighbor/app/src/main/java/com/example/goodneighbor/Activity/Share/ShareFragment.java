package com.example.goodneighbor.Activity.Share;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodneighbor.R;
import com.example.goodneighbor.bean.MyRecycleViewAdapter;

public class ShareFragment extends Fragment {

    //    要展示的对应item的数据，imgs是上方的图片/视图
    //    titles是标题，headsIcon是头像，username是用户名
    private int[] imgs={R.drawable.pingpang1,R.drawable.share2,R.drawable.share4,R.drawable.share9,R.drawable.share10,R.drawable.share11};
    private String[] titles={"电影看书的女人","阿呆的沙雕绘画","帅猫","夕阳下的教学楼","看不见的","电影看书的女人"};
    private int[] headsIcon={R.drawable.user1,R.drawable.user2,R.drawable.user3,R.drawable.user4,R.drawable.user4,R.drawable.user1};
    private String[] usernames={"阿呆","Wacke","肉团","加密","ajia","阿呆"};
    private TextView fab;
    @Nullable
    @Override
    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.share_share,null);


        fab=view.findViewById(R.id.fab);
       fab.setOnClickListener(v -> {
           startActivity(new Intent(getActivity(),SharePublish.class));
       });

        MyRecycleViewAdapter mAdapter=new MyRecycleViewAdapter(getContext(),imgs,titles,headsIcon,usernames);
        RecyclerView recyclerView=view.findViewById(R.id.home_item);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position_share) {
                SharedPreferences shared= getActivity().getSharedPreferences("posting_share", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=shared.edit();
                editor.putInt("position_share",position_share);
                editor.apply();
                Intent i=new Intent(getContext(), ShareGoods1.class);
                startActivity(i);
            }
        });
        return view;
    }
}