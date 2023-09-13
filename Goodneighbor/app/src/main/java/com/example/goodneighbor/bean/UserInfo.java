package com.example.goodneighbor.bean;

public class UserInfo {
    public String email;
        public long rowid; // 行号
        public int xuhao; // 序号
        public String name; // 姓名
        public int age; // 年龄
        public String update_time; // 更新时间
       // public String phone; // 手机号

        public UserInfo() {
            email="";
            rowid = 0L;
            xuhao = 0;
            name = "";
            age = 0;
            update_time = "";
           // phone = "";
        }
}
