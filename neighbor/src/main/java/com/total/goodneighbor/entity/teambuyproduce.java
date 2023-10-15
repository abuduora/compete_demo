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
@TableName("teambuyproduce")
public class teambuyproduce {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private int produce_price;
    private int produce_quantity;
    private String produce_name;

}

