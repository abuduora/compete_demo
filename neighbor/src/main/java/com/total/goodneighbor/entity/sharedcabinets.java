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
@TableName("sharedcabinets")
public class sharedcabinets {
    @TableId(value = "cabinetsnumbers",type = IdType.AUTO)
    private int cabinetsnumbers;
    private int guinumber;

}

