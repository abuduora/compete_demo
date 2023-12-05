package com.example.goodneighbor.Activity.Main;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.goodneighbor.R;

public class PostingsActivity extends AppCompatActivity {
    private AppCompatButton back,publish;
    private EditText title,content;
    private TextView add_images;
    private LinearLayout show_images;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_postings);
        back=findViewById(R.id.tv_postings_back);
        publish=findViewById(R.id.tv_postings_publish);
        title=findViewById(R.id.tv_postings_text_title);
        content=findViewById(R.id.tv_postings_text_content);
        add_images=findViewById(R.id.tv_postings_add_images);
        show_images=findViewById(R.id.tv_postings_images_show);

        //返回上一级
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //发布按钮
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //图片添加
        add_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}