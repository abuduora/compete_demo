package com.gpt.gptdev.entity;

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
@TableName("teambuysum")
public class teambuysum {
    @TableId(value = "PostingsName",type = IdType.AUTO)
    private Char PostingsName;
    private int PostingsNumber;

}

