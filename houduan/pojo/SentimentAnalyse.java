package com.example.houduan.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-18 13:23:31
 */
@Data
@TableName("sentiment_analyse")
public class SentimentAnalyse implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;

	private String content;

	private String analyseTime;

	private Integer sentimentScore;

	private String classification;

	private Date updateTime;

}
