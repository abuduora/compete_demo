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
@TableName("integral")
public class integral {
    @TableId(value = "user_email",type = IdType.AUTO)
    private String user_email;
    private String community_name;
    private int integral;
}