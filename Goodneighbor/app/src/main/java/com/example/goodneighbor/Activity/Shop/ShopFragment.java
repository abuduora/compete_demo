package com.example.goodneighbor.Activity.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.goodneighbor.R;

public class ShopFragment extends Fragment {

    private TextView tv_window1;
    private TextView tv_window2;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shop_main, null);
        tv_window1 = view.findViewById(R.id.tv_bijia);
        tv_window2=view.findViewById(R.id.tv_tuangou);
        tv_window1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), ShopBijiaActivity.class);
                startActivity(i);
            }
        });
        tv_window2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), ShopTuGouActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}