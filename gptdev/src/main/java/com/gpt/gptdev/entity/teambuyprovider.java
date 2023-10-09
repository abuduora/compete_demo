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
@TableName("teambuyprovider")
public class teambuyprovider {
    @TableId(value = "id",type = IdType.AUTO)
    private String id;
    private int produce_number;
    private String provide_name;
    private String produce_name;
}

