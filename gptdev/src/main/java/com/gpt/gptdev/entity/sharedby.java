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
@TableName("sharedby")
public class Sharedby {
    @TableId(value = "sharesnumber",type = IdType.AUTO)
    private String shares;
    private String username;
    private String password;
    private String user_id;
    private int sharenumber;
    private int returnshares;
    private String photo;

}
