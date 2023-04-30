package com.example.houduan.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.houduan.pojo.Comment;
import com.example.houduan.pojo.Statistical;
import com.example.houduan.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistical")
public class StatisticalController {

    @Autowired
    private StatisticalService statisticalService;

    @GetMapping("/list")
    public Map<String, Object> list(){
        Map<String, Object> data = new HashMap<>();
        List<Statistical> statisticals = statisticalService.findAll();
        data.put("statisticals", statisticals);
        data.put("total", statisticals.size());
        return data;   // 返回所有统计信息
    }

    @DeleteMapping("/deleteById/{id}")
    public Integer deleteById(@PathVariable Integer id){
        return statisticalService.deleteById(id);  // 返回1表示删除了一条数据
    }

    @GetMapping("/page")
    public IPage<Statistical> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String areaName){
        IPage<Statistical> data = statisticalService.selectPage(pageNum, pageSize, areaName);
        return data;
    }

    @GetMapping("/selectById/{id}")
    public Statistical selectById(@PathVariable Integer id){
        return statisticalService.getById(id);
    }

    @DeleteMapping("/deleteBatch/")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return statisticalService.deleteBatch(ids);
    }

    @PostMapping("/updateById/{id}")
    public Integer updateById(@PathVariable Integer id){
        return statisticalService.updateById(id);
    }



}
