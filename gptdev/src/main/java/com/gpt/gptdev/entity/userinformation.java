package com.gpt.gptdev.entity;

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
    private int id;
    private String realname;
    private String nickname;
    private int user_integral;
    private String community_name;
    private String user_address;
    private int building_number;
    private char sex;
    private int user_age;
    private String avatar;//longblob
}