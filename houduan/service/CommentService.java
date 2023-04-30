package com.example.houduan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.houduan.pojo.Comment;

import java.util.List;


public interface CommentService {
    List<Comment> findAll();

    Integer deleteById(Integer id);

    IPage<Comment> selectPage(Integer pageNum, Integer pageSize, String scenicArea, String username);

    Comment getById(Integer id);

    boolean deleteBatch(List<Integer> ids);

    Integer updateById(Integer id);

}
