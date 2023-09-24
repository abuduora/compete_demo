//package com.example.goodneighbor.bean;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.goodneighbor.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {
//
//    private List<ImageView> images = new ArrayList<>();
//    public ViewPagerAdapter(){
//        this.images= images;
//    }
//
//    @NonNull
//    @Override
//    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ImageView view= (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.main_viewpager,parent,false);
////        return new ViewPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_viewpager, parent, false));
//        return new ViewPagerViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
//        holder.imageView.setImageResource(images.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return images.size();
//    }
//
//    class ViewPagerViewHolder extends RecyclerView.ViewHolder{
//        ImageView imageView;
//
//        public ViewPagerViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.tvTitle1);
//        }
//    }
//}