package com.example.goodneighbor.Activity.Main;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;
import com.example.goodneighbor.bean.CircleImageView;
import com.example.goodneighbor.util.OkHttp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainRanking extends AppCompatActivity {
    private CircleImageView firstimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ranking);
        firstimage=findViewById(R.id.firstimage);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url= "http://[240e:404:b830:a118:61ce:6331:f25f:c199]:9776/user/rank";
                OkHttp.Get(url, new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Toast.makeText(MainRanking.this, "请检查网络设置", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        runOnUiThread(()-> {
                            List resp;
                            try {
                                resp = Arrays.asList(response.body().bytes());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Uri uri = (Uri) resp.get(0);
                                    firstimage.setImageURI(uri);
                        });
                    }
                });
            }
        }).start();
    }
}