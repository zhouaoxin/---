package com.example.houduan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.houduan.pojo.Comment;
import com.example.houduan.pojo.Scenic;
import com.example.houduan.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scenic")
public class ScenicController {
    @Autowired
    ScenicService scenicService;

    @GetMapping("/list")
    public Map<String, Object> list(){
        Map<String, Object> data = new HashMap<>();
        List<Scenic> scenics = scenicService.findAll();
        data.put("scenics", scenics);
        data.put("total", scenics.size());
        return data;   // 返回所有景点信息
    }

    @DeleteMapping("/deleteById/{id}")
    public Integer deleteById(@PathVariable Integer id){
        return scenicService.deleteById(id);  // 返回1表示删除了一条数据
    }

    @DeleteMapping("/deleteBatch/")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return scenicService.deleteBatch(ids);
    }

    @GetMapping("/page")
    public IPage<Scenic> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String scenicArea){
        IPage<Scenic> data = scenicService.selectPage(pageNum, pageSize, scenicArea);
        return data;
    }

    @GetMapping("/selectById/{id}")
    public Scenic selectById(@PathVariable Integer id){
        return scenicService.getById(id);
    }
}
