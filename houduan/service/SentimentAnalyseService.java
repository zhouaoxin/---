package com.example.houduan.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.houduan.pojo.SentimentAnalyse;

import java.util.List;

public interface SentimentAnalyseService{

    List<SentimentAnalyse> findAll();

    Integer deleteById(Integer id);

    IPage<SentimentAnalyse> selectPage(Integer pageNum, Integer pageSize);

    SentimentAnalyse getById(Integer id);

    boolean deleteBatch(List<Integer> ids);

}

