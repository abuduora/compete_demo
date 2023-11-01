package com.example.goodneighbor.bean;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class FloatingButtonService extends Service {

    private WindowManager windowManager;
    private ButtonView buttonView;

    @Override
    public void onCreate() {
        super.onCreate();

        // 创建悬浮按钮的布局参数
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, // 悬浮窗口类型，需要API级别为26及以上
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, // 不获取焦点，不接收触摸事件
                PixelFormat.TRANSLUCENT); // 设置窗口背景透明

        // 创建悬浮按钮的实例
        buttonView = new ButtonView(this);

        // 获取WindowManager
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // 将悬浮按钮添加到窗口中
        windowManager.addView(buttonView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // 从窗口中移除悬浮按钮
        if (windowManager != null && buttonView != null) {
            windowManager.removeView(buttonView);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}