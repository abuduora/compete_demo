package com.example.goodneighbor.bean;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.goodneighbor.database.BookDatabase;
import com.example.goodneighbor.database.GoodsInfo;
import com.example.goodneighbor.database.ShoppingDBHelper;
import com.example.goodneighbor.util.FileUtil;
import com.example.goodneighbor.util.SharedUtil;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;

public class MainApplication extends Application {
    private static MainApplication mApp; // 声明一个当前应用的静态实例
    public String wechatName; // 自己的微信昵称
    private Socket mSocket; // 声明一个套接字对象

    // 声明一个公共的信息映射对象，可当作全局变量使用
    public HashMap<String, String> infoMap = new HashMap<>();

    // 声明一个书籍数据库对象
    private BookDatabase bookDatabase;

    // 购物车中的商品总数量
    public int goodsCount;

    // 利用单例模式获取当前应用的唯一实例
    public static MainApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this; // 在打开应用时对静态的应用实例赋值
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String uri = String.format("http://[%s]:%d/", NetConst.IP, NetConst.chat_port);
                    mSocket = IO.socket(uri); // 创建指定地址和端口的套接字实例
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        bookDatabase = Room.databaseBuilder(this, BookDatabase.class, "book")
                // 允许迁移数据库（发生数据库变更时，Room默认删除原数据库再创建新数据库。如此一来原来的记录会丢失，故而要改为迁移方式以便保存原有记录）
                .addMigrations()
                // 允许在主线程中操作数据库（Room默认不能在主线程中操作数据库）
                .allowMainThreadQueries()
                .build();

        // 初始化商品信息
        initGoodsInfo();
            }

    private void initGoodsInfo() {
        // 获取共享参数保存的是否首次打开参数
        boolean isFirst = SharedUtil.getInstance(this).readBoolean("first", true);
        // 获取当前App的私有下载路径
        String directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar;
        if (isFirst) {
            // 模拟网络图片下载
            List<GoodsInfo> list = GoodsInfo.getDefaultList();
            for (GoodsInfo info : list) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), info.pic);
                String path = directory + info.id + ".jpg";
                // 往存储卡保存商品图片
                FileUtil.saveImage(path, bitmap);
                // 回收位图对象
                bitmap.recycle();
                info.picPath = path;
            }
            // 打开数据库，把商品信息插入到表中
            ShoppingDBHelper dbHelper = ShoppingDBHelper.getInstance(this);
            dbHelper.openWriteLink();
            dbHelper.insertGoodsInfos(list);
            dbHelper.closeLink();
            // 把是否首次打开写入共享参数
            SharedUtil.getInstance(this).writeBoolean("first", false);
        }
    }


    //在App终止时调用
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("ning", "onTerminate");
    }

    //在配置改变时调用，例如从竖屏变为横屏。
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(new Configuration());
        Log.d("ning", "onConfigurationChanged");
    }

    // 获取书籍数据库的实例
    public BookDatabase getBookDB() {
        return bookDatabase;
    }

    // 获取套接字对象的唯一实例
    public Socket getSocket() {
        return mSocket;
    }
}
