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
@TableName("shareu")
public class shareu {
    @TableId(value = "user_email",type = IdType.AUTO)
    private String user_email;
    private int cabinetsnumbers;
    private int share_return;
    private long photo;//longblob



}

