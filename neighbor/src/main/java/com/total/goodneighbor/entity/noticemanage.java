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
@TableName("noticemanage")
public class noticemanage {
    @TableId(value = "user_email",type = IdType.AUTO)
    private int notice_id;
    private String user_email;
    private String notice_title;
    private String notice_publisher;
}
