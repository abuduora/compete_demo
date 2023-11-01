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
@TableName("postingssum")
public class postingssum {
    @TableId(value = "PostingsName",type = IdType.AUTO)
    private int PostingsNumber;
    private Char PostingsName;
}

