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
@TableName("postingspage")
public class postingspage {
    @TableId(value = "postings_id",type = IdType.AUTO)
    private int postings_coins;
    private String publish_id;
    private String postings_content;
    private String postings_id;
    private String postings_image;
    private String community;
}

