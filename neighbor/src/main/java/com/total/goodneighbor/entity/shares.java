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
@TableName("shares")
public class shares {
    @TableId(value = "sharename",type = IdType.AUTO)
    private int sharenumber;
    private int cost;
    private int returns;
    private long photo;//longblob
    private String sharename;
    private String user_email;

}

