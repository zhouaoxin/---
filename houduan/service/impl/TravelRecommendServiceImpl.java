package com.example.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.houduan.dao.TravelRecommendDao;
import com.example.houduan.pojo.TravelRecommend;
import com.example.houduan.service.TravelRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelRecommendServiceImpl extends ServiceImpl<TravelRecommendDao, TravelRecommend> implements TravelRecommendService {

    @Autowired
    TravelRecommendDao travelRecommendDao;

    @Override
    public List<TravelRecommend> findAll() {
        return travelRecommendDao.selectList(null);
    }

    @Override
    public Integer deleteById(Integer id) {
        return travelRecommendDao.deleteById(id);
    }

    @Override
    public IPage<TravelRecommend> selectPage(Integer pageNum, Integer pageSize, String scenicArea) {
        Page<TravelRecommend> page = new Page<>(pageNum, pageSize);
        QueryWrapper<TravelRecommend> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("scenic_area", scenicArea);
        return travelRecommendDao.selectPage(page,queryWrapper);
    }

    @Override
    public TravelRecommend getById(Integer id) {
        return travelRecommendDao.selectById(id);
    }

    @Override
    public boolean deleteBatch(List<Integer> ids) {
        return this.removeBatchByIds(ids);
    }
}
