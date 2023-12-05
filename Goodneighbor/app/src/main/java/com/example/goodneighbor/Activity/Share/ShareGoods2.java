package com.example.goodneighbor.Activity.Share;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShareGoods2 extends AppCompatActivity {
    //    要展示的对应item的数据，imgs是上方的图片/视图
    //    titles是标题，headsIcon是头像，username是用户名
    private File[] imgs;
    private int[] imgs_posting={R.drawable.pingpang1,R.drawable.share2,R.drawable.share4,R.drawable.share9,R.drawable.share10,R.drawable.share11,R.drawable.share2,R.drawable.share4,R.drawable.share8,R.drawable.share9};
    private String[] titles={"电影看书的女人","阿呆的沙雕绘画","帅猫","夕阳下的教学楼","看不见的","电影看书的女人","阿呆的沙雕绘画","帅猫","夕阳下的教学楼","看不见的"};
    private int[] headsIcon={R.drawable.user1,R.drawable.user2,R.drawable.user3,R.drawable.user4,R.drawable.user4,R.drawable.user1,R.drawable.user2,R.drawable.user3,R.drawable.user4,R.drawable.user4};
    private String[] usernames={"阿呆","Wacke","肉团","加密","ajia","阿呆","Wacke","肉团","加密","ajia"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_goods2);
        ImageView shop_icons_xiangqing=findViewById(R.id.shop_icons_xiangqing);
        TextView tit_desc_xiangqing=findViewById(R.id.tit_desc_xiangqing);
        SharedPreferences shared=getSharedPreferences("share",MODE_PRIVATE);
        int position_share=shared.getInt("position_share",0);

        String url="https://edu-guli1100.oss-cn-beijing.aliyuncs.com/2023/10/30/6b397d6c262345908e64e1279e83320d.png";
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                InputStream is=response.body().byteStream();
                Bitmap bitmap= BitmapFactory.decodeStream(is);
                String mediaType=response.body().contentType().toString();
                long length=response.body().contentLength();
                runOnUiThread(()->{
                    shop_icons_xiangqing.setImageBitmap(bitmap);
                });
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }});

        tit_desc_xiangqing.setText(titles[position_share]);

        ImageView btn1=findViewById(R.id.back_icons);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
