package com.example.houduan.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("statistical")
public class Statistical implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String scenicName;
	/**
	 * 
	 */

	private String areaName;


	private Integer commentNums;
	/**
	 * 
	 */
	private Double commentAvgScore;
	/**
	 * 
	 */
	private String sentimentType;
	/**
	 * 
	 */
	private Integer manNums;
	/**
	 * 
	 */
	private Integer womenNums;
	/**
	 * 
	 */
	private Integer manAgeAvg;
	/**
	 * 
	 */
	private Integer womenAgeAvg;
	/**
	 * 
	 */
	private String vehicle;

}
