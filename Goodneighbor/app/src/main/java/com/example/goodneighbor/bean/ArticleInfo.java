package com.example.goodneighbor.bean;

import android.graphics.Bitmap;

public class ArticleInfo {
    //数据流,后期更改数据库
    private int space=5;//设置RecyclerView控件的item的上下间距
    //要展示的对应item的数据，imgs是上方的图片/视图
    //titles是标题，headsIcon是头像，username是用户名
//    private int[] imgs={R.drawable.share1,R.drawable.share2,R.drawable.share4,R.drawable.share5,R.drawable.share5,R.drawable.share1,R.drawable.share2,R.drawable.share4,R.drawable.share5,R.drawable.share5};
//    private String[] titles={"电影看书的女人","阿呆的沙雕绘画","帅猫","夕阳下的教学楼","看不见的","电影看书的女人","阿呆的沙雕绘画","帅猫","夕阳下的教学楼","看不见的"};
//    private int[] headsIcon={R.drawable.user1,R.drawable.user2,R.drawable.user3,R.drawable.user4,R.drawable.user4,R.drawable.user1,R.drawable.user2,R.drawable.user3,R.drawable.user4,R.drawable.user4};
//    private String[] usernames={"阿呆","Wacke","肉团","加密","ajia","阿呆","Wacke","肉团","加密","ajia"};

    private String title;
    private  String username;
    private Bitmap headIcon;
    private Bitmap img;


    //获取标题
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //获取用户名称
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username=username;
    }

    //获取所需图片
    public Bitmap getImg(){
        return this.img;
    }
    public void setImg(Bitmap img){
        this.img=img;
    }

    //获取用户头像
    public Bitmap getHead(){
        return this.headIcon;
    }
    public void setHead(Bitmap head){
        this.headIcon=head;
    }
}