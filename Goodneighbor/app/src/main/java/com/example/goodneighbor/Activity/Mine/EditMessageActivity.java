package com.example.goodneighbor.Activity.Mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;

public class EditMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private View button;
    private ImageView iv_image;
    private ActivityResultLauncher<Intent> mRresultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_message);
        iv_image = findViewById(R.id.iv_image);
        iv_image.setOnClickListener(this);
        //跳转到系统相册，选择图片，并返回
        mRresultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK){
                    Intent intent=result.getData();
                    Uri picUri=intent.getData();
                    if(picUri !=null){
                       iv_image.setImageURI(picUri);
                        Log.d("image","picUri"+picUri.toString());
                    }
                }
            }
        });

        //跳转到我的
        button = findViewById(R.id.btn_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(EditMessageActivity.this, MineFragment.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.btn_flish);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(EditMessageActivity.this, MineFragment.class);
                startActivity(intent);
            }
        });

    }
    //添加头像
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_image){
            Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            mRresultLauncher.launch(intent);
    }
}
}