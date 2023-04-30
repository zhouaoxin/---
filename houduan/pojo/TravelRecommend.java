package com.example.houduan.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("travel_recommend")
public class TravelRecommend {

    private static final long serialVersionUID = 1L;


    @TableId
    private Integer id;

    private Integer scenicId;

    private String scenicArea;

    private Integer scenicIntroduce;

    private Double scenicScore;

    private String travelTools;
}
