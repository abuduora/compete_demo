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
@TableName("sharefix")
public class sharefix {
    @TableId(value = "cabinetsnumbers",type = IdType.AUTO)
    private int cabinetsnumbers;
    private int guinumber;
    private int fix;

}

