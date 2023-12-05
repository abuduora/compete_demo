package com.example.goodneighbor.database;

import com.example.goodneighbor.R;

import java.util.ArrayList;

public class GoodsInfo{

    public int id;
    // 名称
    public String name;
    // 描述
    public String description;
    // 价格
    public float price;
    // 大图的保存路径
    public String picPath;
    // 大图的资源编号
    public int pic;

    // 声明一个商品的名称数组
    private static String[] mNameArray = {
            "无淀粉火腿", "鸡蛋", "精选五花肉", "西红柿", "菠菜", "土豆"
    };
    // 声明一个商品的描述数组
    private static String[] mDescArray = {
            "无淀粉火腿 好吃不胖，美味绿色，精选肉类加工",
            "鸡蛋 新鲜土鸡蛋，富含丰富蛋白质，严格把控除菌",
            "精选五花肉 肥瘦相间，口感鲜嫩，适合煮、炖、炒等多种烹饪方式",
            "西红柿 鲜红色，酸甜可口，富含维生素C，可生食、炒菜、做汤",
            "菠菜 翠绿色，口感嫩滑，富含铁、维生素C等营养素，适合炒菜、做汤",
            "土豆 富含淀粉，可烤、煮、炒等多种烹饪方式"
    };
    // 声明一个商品的价格数组
    private static float[] mPriceArray = {19, 7, 13, 4, 3, 5};
    // 声明一个商品的大图数组
    private static int[] mPicArray = {
            R.drawable.c1, R.drawable.c2, R.drawable.c3,
            R.drawable.c4, R.drawable.c5, R.drawable.c6
    };

    // 获取默认的信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.id = i;
            info.name = mNameArray[i];
            info.description = mDescArray[i];
            info.price = mPriceArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}

