package com.example.goodneighbor.Activity.Share;

import static com.example.goodneighbor.util.GetBaiduDistanceUtils.getDistanceMeter;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.goodneighbor.R;


public class BaiduMapView extends Activity {
    public static MapView mMapView = null;
    BaiduMap mBaiduMap = null;
    LocationClient mLocationClient = null;
    private BaiduMapView context;
    private Location location;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baidu_location);
        //地图合规隐私
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);
        this.context = this;
        LocationClient.setAgreePrivacy(true);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.baidu_mapView);
        //获取到地图
        mBaiduMap = mMapView.getMap();
        //设置地图放大的倍数
        init();
        //设置地图定位的一些参数，如定位图标，精度圈颜色等
        configure();
        //定位初始化
        init_location();

        //坐标等待
        location location1=new location();
        location1.baidu_distance(116.43451, 39.9985);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    /**
     * 继承抽象类BDAbstractListener并重写其onReceieveLocation方法来获取定位数据，并将其传给MapView。
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
        }
    }

    /**
     * 设置地图放大的倍数
     */
    public void init() {
        //设置地图放大的倍数
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18f);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    /**
     * 自定义内容:
     * 参数说明
     * (1)定位模式 地图SDK支持三种定位模式：NORMAL（普通态）, FOLLOWING（跟随态）, COMPASS（罗盘态）
     * （2）是否开启方向
     * （3）自定义定位图标 支持自定义定位图标样式，
     * （4）自定义精度圈填充颜色
     * （5）自定义精度圈边框颜色
     */
    public void configure() {
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true,
                BitmapDescriptorFactory.fromResource(R.drawable.share_locate),
                0xAAFFFF88, 0xAA00FF00));
    }

    /**
     * 定位的初始化
     */
    public void init_location() {
        mBaiduMap.setMyLocationEnabled(true);
        try {
            mLocationClient = new LocationClient(getApplicationContext());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        //设置locationClientOption
        mLocationClient.setLocOption(option);
        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();
    }

    public class location extends BDAbstractLocationListener {
        public double user_latitude;
        public double user_longitude;

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            user_latitude = location.getLatitude();//纬度
            user_longitude = location.getLongitude();//经度
        }

        public void baidu_distance(double locker_longitude, double locker_latitude) {
            //计算距离
            double distance;
            distance = getDistanceMeter(locker_longitude, locker_latitude, user_latitude, user_longitude);
            String title1=String.valueOf(distance);
            String title2="距离您：";
            String context=title2+title1;
            //放置
            LatLng point = new LatLng(locker_latitude, locker_longitude);
            OverlayOptions options = new MarkerOptions()
                    .position(point)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.way_point_start))
                    .zIndex(2);
            mBaiduMap.addOverlay(options);
        }
    }
}