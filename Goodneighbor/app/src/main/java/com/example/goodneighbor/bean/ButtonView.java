package com.example.goodneighbor.bean;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.goodneighbor.R;

public class ButtonView extends View {
    private Paint paint;
    private int radius;

    public ButtonView(Context context) {
        super(context);
        init();
    }

    public ButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 设置悬浮按钮的大小
        int size = getResources().getDimensionPixelSize(R.dimen.button_size);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制悬浮按钮
        int width = getWidth();
        int height = getHeight();
        radius = Math.min(width, height) / 2;
        canvas.drawCircle(width / 2, height / 2, radius, paint);
    }
}
