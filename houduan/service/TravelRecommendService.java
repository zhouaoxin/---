package com.example.houduan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.houduan.pojo.TravelRecommend;

import java.util.List;

public interface TravelRecommendService {

    List<TravelRecommend> findAll();

    Integer deleteById(Integer id);

    IPage<TravelRecommend> selectPage(Integer pageNum, Integer pageSize, String scenicName);

    TravelRecommend getById(Integer id);

    boolean deleteBatch(List<Integer> ids);

}
