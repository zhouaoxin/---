package com.example.houduan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.houduan.dao.CommentDao;
import com.example.houduan.pojo.Comment;
import com.example.houduan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao,Comment> implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Override
    public List<Comment> findAll() {
        return commentDao.selectList(null);
    }

    @Override
    public Integer deleteById(Integer id) {
        return commentDao.deleteById(id);
    }

    @Override
    public IPage<Comment> selectPage(Integer pageNum, Integer pageSize, String scenicArea, String username) {
        Page<Comment> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("scenic_area", scenicArea);
        queryWrapper.like("observer_name", username);
        return commentDao.selectPage(page,queryWrapper);
    }

    @Override
    public Comment getById(Integer id) {
        Comment comment = commentDao.selectById(id);
        return comment;
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
