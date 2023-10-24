package com.example.goodneighbor.Activity.Circle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.goodneighbor.R;

public class CircleFragment extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.circle_circle,null);
        LinearLayout chat_text=view.findViewById(R.id.chat_text);
        chat_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), FriendChatActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}