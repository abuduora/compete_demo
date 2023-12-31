package com.total.goodneighbor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("userinformation")
public class userinformation {
    @TableId(value = "user_email",type = IdType.AUTO)
    private String user_email;
    private String id;
    private String realname;
    private String nickname;
    private int integral;
    private String community_name;
    private String user_address;
    private String building_number;
    private String sex;
    private int user_age;
    private String avatar;//longblob
    private String phone;
}