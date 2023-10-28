package com.example.goodneighbor.Activity.Mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;
import com.example.goodneighbor.util.OkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_back,btn_finish;
    private ImageView iv_image;
    private ActivityResultLauncher<Intent> mRresultLauncher;
    private  Uri picUri;
    private EditText et_name,et_sex,et_address,et_phone,et_buildingnumber;
    SharedPreferences shared=getSharedPreferences("share",MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_message);
        iv_image = findViewById(R.id.iv_image);
        et_name=findViewById(R.id.et_name);
        et_sex=findViewById(R.id.et_sex);
        et_address=findViewById(R.id.et_address);
        et_phone=findViewById(R.id.et_phone);
        et_buildingnumber=findViewById(R.id.et_buildingnumber);

        iv_image.setOnClickListener(this);
        //跳转到系统相册，选择图片，并返回
        mRresultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK){
                    Intent intent=result.getData();
                   picUri=intent.getData();
                    if(picUri !=null){
                       iv_image.setImageURI(picUri);
                        SharedPreferences.Editor editor=shared.edit();
                        editor.putString("picUri",picUri.toString());
                        editor.commit();
                        Log.d("image","picUri"+picUri.toString());
                    }
                }
            }
        });

        //跳转到我的
       btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(v -> finish());
       btn_finish = findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url="http://[240e:404:2521:9cb7:494f:2883:4f34:1ee]:9776/user/message";
                        RequestBody requestBody=new FormBody.Builder()
                                .add("email",shared.getString("email",""))
                                .add("uri",picUri.toString())
                                .add("nickname",et_name.getText().toString())
                                .add("sex",et_sex.getText().toString())
                                .add("phone",et_phone.getText().toString())
                                .add("buildingnumber",et_buildingnumber.getText().toString())
                                .add("address",et_address.getText().toString())
                                .build();
                        OkHttp.Post(url, requestBody, new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                Toast.makeText(EditMessageActivity.this, "修改个人信息失败，请检查网络设置", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                Toast.makeText(EditMessageActivity.this, "修改个人信息成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
                et_name.setText(et_name.getText().toString());
                et_sex.setText(et_sex.getText().toString());
                et_address.setText(et_address.getText().toString());
                SharedPreferences.Editor editor=shared.edit();
                String name="快来给自己取一个好听的名字吧！";
                String email="请输入地址";
                String phone="请输入手机号";
                String buildingnumber="请输入楼号";
                if(et_name.getText().toString()!=name)
                  editor.putString("nickname",et_name.getText().toString());
                if(et_address.getText().toString()!=email)
                  editor.putString("address",et_address.getText().toString());
                editor.putString("sex",et_sex.getText().toString());
                if(et_phone.getText().toString()!=phone)
                  editor.putString("phone",et_phone.getText().toString());
                if(et_buildingnumber.getText().toString()!=buildingnumber)
                    editor.putString("buildingnumber",et_buildingnumber.getText().toString());
                editor.commit();
                Intent intent = new Intent(EditMessageActivity.this, MineFragment.class);
                Bundle bundle = new Bundle();
                bundle.putString("nickname",et_name.getText().toString());
                bundle.putString("uri",picUri.toString());
                intent.putExtras(bundle);
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