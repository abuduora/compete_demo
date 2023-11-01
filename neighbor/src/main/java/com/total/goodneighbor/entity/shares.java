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
    @TableId(value = "share_id",type = IdType.AUTO)
    private int share_id;
    private String email;
    private String share_name;
    private int integral;
    private String image;
}

