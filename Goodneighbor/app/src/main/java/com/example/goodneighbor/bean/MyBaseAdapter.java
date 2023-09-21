package com.example.goodneighbor.bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goodneighbor.Activity.Main.MainFragment;
import com.example.goodneighbor.Activity.Main.PageActivity;
import com.example.goodneighbor.Activity.Share.ShareFragment;
import com.example.goodneighbor.R;


public class MyBaseAdapter extends BaseAdapter{
    //共享数据适配
    private String[] titles={"键盘","水杯","书","键盘","书签","键盘"};
    private String[] shareBox={"一号共享柜","二号共享柜","三号共享柜"};
    private int[] icons={R.drawable.share1,R.drawable.share2,R.drawable.share3,R.drawable.share4,R.drawable.share5};
    private int position;
    private View convertView;
    private ViewGroup parent;
    private Context ShareFragment;

    @Override
    public int getCount(){       //得到item的总数
        return titles.length;    //返回ListView Item条目代表的对象
    }

    @Override
    public Object getItem(int position){
        return titles[position]; //返回item的数据对象
    }
    @Override
    public long getItemId(int position){
        return position;         //返回item的id
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        this.position = position;//获取item中的View视图
        this.convertView = convertView;
        this.parent = parent;
        ViewHolder holder;
        if(convertView==null){
//            convertView=View.inflate(R.layout.share_share,null);
            holder=new ViewHolder();
            holder.title=convertView.findViewById(R.id.tv_title);
            holder.shareBox=convertView.findViewById(R.id.tv_share_boxNavagation);
            holder.iv=convertView.findViewById(R.id.iv);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.title.setText(titles[position]);
        holder.shareBox.setText(shareBox[position]);
        holder.iv.setImageResource(icons[position]);
        return convertView;
    }
    class ViewHolder{
        TextView title;
        TextView shareBox;
        ImageView iv;
    }
}