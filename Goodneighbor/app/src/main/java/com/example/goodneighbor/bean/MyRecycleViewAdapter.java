package com.example.goodneighbor.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodneighbor.R;

/**
 * Created by 彭老希 on 2021/3/24.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {
    private Context context;
    private String[] title;
    private String[] username;
    private int[] Img;
    private int[] head;

    private OnItemClickListener mOnItemClickListener;

    /**
     * 构造方法
     *
     * @param context
     * @param Img
     * @param title
     * @param username
     */
    public MyRecycleViewAdapter(Context context, int[] Img, String[] title,int[] head,String[] username) {
        this.context = context;
        this.Img = Img;
        this.head=head;
        this.title = title;
        this.username= username;
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        /**
         * 第三步，得到item的布局，然后为其设置OnClickListener监听器
         */
        View itemRoot = LayoutInflater.from(context).inflate(R.layout.share_commoddity_item, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(itemRoot);
        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 第五步，使用getTag方法获取点击的item的position
                 */
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, (int) v.getTag());
                }
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.img.setImageDrawable(context.getResources().getDrawable(Img[position]));
        holder.title.setText(title[position]);
        holder.head.setImageDrawable(context.getResources().getDrawable(head[position]));
        holder.username.setText(username[position]);
        /**
         * 第四步，将position保存在itemView的Tag中，以便点击时进行获取
         */
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return head.length;
    }

    /**
     * 在Activity中设置item点击事件的方法第一步：
     * 第一步，定义接口,在activity里面使用setOnItemClickListener方法并创建此接口的对象、实现其方法
     */
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 第二步，为Activity提供设置OnItemClickListener的接口
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        //初始化控件
        ImageView img,head;
        TextView title,username;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.home_item_img);
            title=itemView.findViewById(R.id.home_item_title);
            head=itemView.findViewById(R.id.home_item_head);
            username=itemView.findViewById(R.id.home_item_username);
        }
    }
}

