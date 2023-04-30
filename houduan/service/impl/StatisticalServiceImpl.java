package com.example.houduan.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.houduan.dao.StatisticalDao;
import com.example.houduan.pojo.Statistical;
import com.example.houduan.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticalServiceImpl extends ServiceImpl<StatisticalDao, Statistical> implements StatisticalService {

    @Autowired
    private StatisticalDao statisticalDao;

    @Override
    public List<Statistical> findAll() {
        return statisticalDao.selectList(null);
    }

    @Override
    public Integer deleteById(Integer id) {
        return statisticalDao.deleteById(id);
    }

    @Override
    public IPage<Statistical> selectPage(Integer pageNum, Integer pageSize, String areaName) {
        Page<Statistical> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Statistical> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("area_name", areaName);
        return statisticalDao.selectPage(page,queryWrapper);
    }

    @Override
    public Statistical getById(Integer id) {
        return statisticalDao.selectById(id);
    }

    @Override
    public boolean deleteBatch(List<Integer> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public Integer updateById(Integer id) {
        return this.updateById(id);
    }
}
