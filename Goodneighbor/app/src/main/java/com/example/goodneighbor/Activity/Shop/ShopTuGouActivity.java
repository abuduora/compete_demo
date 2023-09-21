package com.example.goodneighbor.Activity.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;

public class ShopTuGouActivity extends AppCompatActivity {

    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_tu_gou);
        btn_back = findViewById(R.id.btn_backK);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(ShopTuGouActivity.this,ShopMainActivity.class);
                startActivity(intent);
            }
        });
    }
}