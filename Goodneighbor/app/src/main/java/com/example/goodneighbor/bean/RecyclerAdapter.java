package com.example.goodneighbor.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodneighbor.R;

import org.w3c.dom.Text;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Map<String,String>> mapList;

    public RecyclerAdapter(List<Map<String,String>>mapList,Context context) {
        this.context=context;
        this.mapList=mapList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.share_item,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Map<String,String>map=mapList.get(position);
        MyViewHolder myViewHolder=(MyViewHolder) holder;
        myViewHolder.title.setText(map.get("title"));
        myViewHolder.Price.setText(map.get("price"));
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView Price;
            public MyViewHolder(View v){
            super(v);
            title=(TextView)v.findViewById(R.id.tv_title);
            Price=(TextView)v.findViewById(R.id.tv_share_boxNavagation);
            }
        }
}
