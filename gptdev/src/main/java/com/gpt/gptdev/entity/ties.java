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
@TableName("ties")
public class ties {
    @TableId(value = "tiesnumber",type = IdType.AUTO)
   private int tiesnumber;
    private int user_id;
    private  String Images;
    private String Comomunity;
    private String cntent;


}

