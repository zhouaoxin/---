package com.example.houduan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.houduan.pojo.Scenic;

import java.util.List;

public interface ScenicService {
    List<Scenic> findAll();

    Integer deleteById(Integer id);

    IPage<Scenic> selectPage(Integer pageNum, Integer pageSize, String scenicArea);

    Scenic getById(Integer id);

    boolean deleteBatch(List<Integer> ids);

    Integer updateById(Integer id);
}
