package com.example.houduan.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("scenic")
public class Scenic {
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer scenicId;

    private String scenicName;

    private String scenicArea;

    private String scenicIntroduce;

    private String scenicScore;

    private String scenicTag;
}
