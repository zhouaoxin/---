package com.example.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.houduan.dao.SentimentAnalyseServiceDao;
import com.example.houduan.pojo.Comment;
import com.example.houduan.pojo.SentimentAnalyse;
import com.example.houduan.service.SentimentAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentimentAnalyseServiceImpl extends ServiceImpl<SentimentAnalyseServiceDao, SentimentAnalyse> implements SentimentAnalyseService {

    @Autowired
    private SentimentAnalyseServiceDao sentimentAnalyseServiceDao;

    @Override
    public List<SentimentAnalyse> findAll() {
        return sentimentAnalyseServiceDao.selectList(null);
    }

    @Override
    public Integer deleteById(Integer id) {
        return sentimentAnalyseServiceDao.deleteById(id);
    }

    @Override
    public IPage<SentimentAnalyse> selectPage(Integer pageNum, Integer pageSize) {
        Page<SentimentAnalyse> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SentimentAnalyse> queryWrapper = new QueryWrapper<>();
        return sentimentAnalyseServiceDao.selectPage(page,queryWrapper);
    }

    @Override
    public SentimentAnalyse getById(Integer id) {
        return sentimentAnalyseServiceDao.selectById(id);
    }

    @Override
    public boolean deleteBatch(List<Integer> ids) {
        return this.removeBatchByIds(ids);
    }
}
