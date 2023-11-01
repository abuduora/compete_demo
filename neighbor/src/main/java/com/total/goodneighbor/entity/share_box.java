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
@TableName("share_box")
public class share_box {
    @TableId(value = "box_id",type = IdType.AUTO)
    private int box_id;
    private int have;
    private String things;
    private String community;
}

