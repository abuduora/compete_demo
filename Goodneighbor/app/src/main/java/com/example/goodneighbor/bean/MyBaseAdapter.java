package com.example.goodneighbor.bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goodneighbor.R;


public class MyBaseAdapter extends BaseAdapter{
    //共享数据适配
    private String[] titles={"桌子","苹果","蛋糕","线衣","猕猴桃","围巾"};
    private String[] prices={"1800元","10元/kg","300元","350元","10元/kg","280元"};
    private  int[] icons={R.drawable.user1,R.drawable.user2,R.drawable.user3,
            R.drawable.user4,R.drawable.user1,R.drawable.user4};

    private int position;
    private View convertView;
    private ViewGroup parent;
    private Context PageActivity;

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
            convertView=View.inflate(PageActivity,R.layout.share_share, null);
            holder=new ViewHolder();
            holder.title=convertView.findViewById(R.id.tv_title);
            holder.price=convertView.findViewById(R.id.price);
            holder.iv=convertView.findViewById(R.id.iv);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.title.setText(titles[position]);
        holder.price.setText(prices[position]);
        holder.iv.setImageResource(icons[position]);
        return convertView;
    }
    class ViewHolder{
        TextView title;
        TextView price;
        ImageView iv;
    }
}