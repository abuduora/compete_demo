package com.example.goodneighbor.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodneighbor.R;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //
    private Context mContext; // 声明一个上下文对象
    private List<ArticleInfo> mArticleList; // 部件列表
    private ImageView share_commoddity_item;

    //加载布局文件并返回MyViewHolder对象
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup vg, int viewType) {
        // 根据布局文件item_article.xml生成视图对象
        View v = LayoutInflater.from(mContext).inflate(R.layout.share_commoddity_item, vg, false);
        return new MyViewHolder(v);
    }
    public ArticleAdapter(Context context, List<ArticleInfo> articleList) {
        mContext = context;
        mArticleList = articleList;
    }

    @Override
    //获取数据并显示到对应控件
    public void onBindViewHolder(RecyclerView.ViewHolder vh, final int position) {
        //给我的四个控件获取一下数据，注意不同类型调用不同的方法，设置图片用setImageResource（），设置文字用setText（）
        MyViewHolder holder = (MyViewHolder) vh;
        ArticleInfo article = mArticleList.get(position);
        holder.img.setImageBitmap(article.getImg());
        holder.title.setText(article.getTitle());
        holder.head.setImageBitmap(article.getHead());
        holder.username.setText(article.getUsername());
    }
    //图片圆边角
    public Bitmap roundCorner(Bitmap bitmap){
        if(bitmap!=null){
            throw new NullPointerException("Bitmap can't be null");
        }
        int outWidth=20;
        int outHeight=20;
        int radius=20;

        //初始化缩放比
        float widthScale = outWidth * 1.0f / bitmap.getWidth();
        float heightScale = outHeight * 1.0f / bitmap.getHeight();
        Matrix matrix = new Matrix();

        // 初始化绘制纹理图
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // 根据控件大小对纹理图进行拉伸缩放处理
        bitmapShader.setLocalMatrix(matrix);

        // 初始化目标bitmap
        Bitmap targetBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);

        // 初始化目标画布
        Canvas targetCanvas = new Canvas(targetBitmap);

        // 初始化画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);

        // 利用画笔将纹理图绘制到画布上面
        targetCanvas.drawRoundRect(new RectF(0, 0, outWidth, outWidth), radius, radius, paint);

        return targetBitmap;
    }

    @Override
    public int getItemCount() {
        //获取列表条目总数
        return mArticleList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        //初始化控件
        ImageView img,head;
        TextView title,username;

        //尝试
        public TextView tv_seq; // 声明一个文本视图对象
        public TextView tv_title; // 声明一个文本视图对象
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.home_item_img);
            title=itemView.findViewById(R.id.home_item_title);
            head=itemView.findViewById(R.id.home_item_head);
            username=itemView.findViewById(R.id.home_item_username);
        }
    }
}


