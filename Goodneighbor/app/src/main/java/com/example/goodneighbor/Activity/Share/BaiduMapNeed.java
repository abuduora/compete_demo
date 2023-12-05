package com.example.goodneighbor.Activity.Share;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.common.BaiduMapSDKException;

public class BaiduMapNeed extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setAgreePrivacy(this,true);
        SDKInitializer.setAgreePrivacy(getApplicationContext(),true);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

    }
    public static void setAgreePrivacy(Context context, boolean isEnable){
        // 是否同意隐私政策，默认为false
        SDKInitializer.setAgreePrivacy(context, isEnable);
        try {
            // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
            SDKInitializer.initialize(context);
        } catch (BaiduMapSDKException e) {
        }
    }
}
