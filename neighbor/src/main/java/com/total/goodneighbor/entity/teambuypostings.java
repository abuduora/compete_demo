package com.total.goodneighbor.entity;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("teambuypostings")
public class teambuypostings {
    @TableId(value = "id",type = IdType.AUTO)
    private Char id;
    private String comment_id;
    private String reply_id;
    private String conent;
    private String from_id;
    private String to_uid;
}

