package com.example.goodneighbor.Activity.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;

public class ShopMainActivity extends AppCompatActivity {

    private View tv_bijia;
    private View tv_tuangou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);
        tv_bijia = findViewById(R.id.tv_bijia);
        tv_tuangou = findViewById(R.id.tv_tuangou);
        tv_bijia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(ShopMainActivity.this,ShopBijiaActivity.class);
                startActivity(intent);
            }
        });
        tv_tuangou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(ShopMainActivity.this,ShopTuGouActivity.class);
                startActivity(intent);
            }
        });
    }
}