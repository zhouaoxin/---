package com.example.houduan.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer userId;

    private Integer scenicId;

    private String observerName;

    private Date commentTime;

    private String scenicArea;

    private String textContent;

    private String sentiment;
}
