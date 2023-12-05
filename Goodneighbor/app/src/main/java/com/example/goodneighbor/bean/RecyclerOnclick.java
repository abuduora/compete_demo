package com.example.goodneighbor.bean;

public class RecyclerOnclick extends Adapter{
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listenser) {
        this.listener = listenser;
    }
}