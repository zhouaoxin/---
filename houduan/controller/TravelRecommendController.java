package com.example.houduan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.houduan.pojo.TravelRecommend;
import com.example.houduan.service.TravelRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/travel_recommend")
public class TravelRecommendController {

    @Autowired
    private TravelRecommendService travelRecommendService;

    @GetMapping("/list")
    public Map<String, Object> list(){
        Map<String, Object> data = new HashMap<>();
        List<TravelRecommend> travelRecommends = travelRecommendService.findAll();
        data.put("travelRecommends", travelRecommends);
        data.put("total", travelRecommends.size());
        return data;   // 返回所有统计信息
    }

    @DeleteMapping("/deleteById/{id}")
    public Integer deleteById(@PathVariable Integer id){
        return travelRecommendService.deleteById(id);  // 返回1表示删除了一条数据
    }

    @GetMapping("/page")
    public IPage<TravelRecommend> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(defaultValue = "") String scenicArea){
        IPage<TravelRecommend> data = travelRecommendService.selectPage(pageNum, pageSize, scenicArea);
        return data;
    }

    @GetMapping("/selectById/{id}")
    public TravelRecommend selectById(@PathVariable Integer id){
        return travelRecommendService.getById(id);
    }

    @DeleteMapping("/deleteBatch/")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return travelRecommendService.deleteBatch(ids);
    }


}
