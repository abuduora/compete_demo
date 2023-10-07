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
@TableName("usergoodsmanage")
public class usergoodsmanage {
    @TableId(value = "user_email",type = IdType.AUTO)
    private int goods_id;
    private String user_email;
    private int goods_name;

}

