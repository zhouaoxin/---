package com.example.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.houduan.dao.ScenicDao;
import com.example.houduan.pojo.Scenic;
import com.example.houduan.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicServiceImpl extends ServiceImpl<ScenicDao, Scenic> implements ScenicService {

    @Autowired
    ScenicDao scenicDao;
    @Override
    public List<Scenic> findAll() {
        return scenicDao.selectList(null);
    }

    @Override
    public Integer deleteById(Integer id) {
        return scenicDao.deleteById(id);
    }

    @Override
    public IPage<Scenic> selectPage(Integer pageNum, Integer pageSize, String scenicArea) {
        Page<Scenic> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Scenic> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("scenic_area", scenicArea);
        return scenicDao.selectPage(page,queryWrapper);
    }

    @Override
    public Scenic getById(Integer id) {
        return scenicDao.selectById(id);
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
