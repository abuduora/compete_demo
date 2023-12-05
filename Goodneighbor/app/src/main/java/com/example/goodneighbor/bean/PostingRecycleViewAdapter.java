package com.example.goodneighbor.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.goodneighbor.R;

/**
 * Created by 彭老希 on 2021/3/24.
 */

public class PostingRecycleViewAdapter extends RecyclerView.Adapter<PostingRecycleViewAdapter.MainViewHolder> {
    private Context context;
    private String[] itemText;
    private int[] user_head;
    private String[] user_title;
    private String[] user_name;

    private OnItemClickListener mOnItemClickListener;

    /**
     * 构造方法
     *
     * @param context
     * @param user_head
     * @param user_title
     * @param user_name
     */
    public PostingRecycleViewAdapter(Context context, int[] user_head, String[] user_title,String[] user_name) {
        this.context = context;
        this.user_head = user_head;
        this.user_title = user_title;
        this.user_name=user_name;
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    public MainViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        /**
         * 第三步，得到item的布局，然后为其设置OnClickListener监听器
         */
        View itemRoot = LayoutInflater.from(context).inflate(R.layout.main_posting_item, parent, false);
        MainViewHolder mainViewHolder = new MainViewHolder(itemRoot);
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
        return mainViewHolder;
    }

    public void onBindViewHolder(MainViewHolder holder, int position) {

        holder.usre_head.setImageDrawable(context.getResources().getDrawable(user_head[position]));
        holder.user_title.setText(user_title[position]);
        holder.user_name.setText(user_name[position]);
        /**
         * 第四步，将position保存在itemView的Tag中，以便点击时进行获取
         */
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return user_head.length;
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


    class MainViewHolder extends RecyclerView.ViewHolder {
        ImageView usre_head;
        TextView user_name,user_title;

        public MainViewHolder(View itemView) {
            super(itemView);
            usre_head = (ImageView) itemView.findViewById(R.id.tv_head);
            user_name=(TextView)itemView.findViewById(R.id.tv_name) ;
            user_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
