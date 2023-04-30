package com.example.houduan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.houduan.pojo.Statistical;

import java.util.List;


public interface StatisticalService {
    List<Statistical> findAll();

    Integer deleteById(Integer id);

    IPage<Statistical> selectPage(Integer pageNum, Integer pageSize, String scenicName);

    Statistical getById(Integer id);

    boolean deleteBatch(List<Integer> ids);

    Integer updateById(Integer id);
}
