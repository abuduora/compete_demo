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
@TableName("postingconmment")
public class postingconmment {
    @TableId(value = "comment_id",type = IdType.AUTO)
    private int comment_id;
    private String user_email;
    private String content;
    private String postings_id;
    private String parent_id;
    private String date;
}

