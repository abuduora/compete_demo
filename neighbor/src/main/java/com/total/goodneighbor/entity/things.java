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
@TableName("things")
public class things {
    @TableId(value = "tid",type = IdType.AUTO)
    private int tid;
    private String email;
    private String tname;
    private int tprice;
    private String url;
    private int status;
    private int num;
    private int sid;
    private int integral;
}

