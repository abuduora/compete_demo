package com.example.goodneighbor.Activity.Shop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.goodneighbor.R;

public class ShopFragment extends Fragment {

    private ImageView tv_window1;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.share_publish, null);
        tv_window1 = view.findViewById(R.id.share_photon);
        tv_window1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tv_window1=new Intent(getActivity(),Fragment.class);
                startActivity(tv_window1);
            }
        });
        return view;
    }
}