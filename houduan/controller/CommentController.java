package com.example.houduan.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.houduan.pojo.Comment;
import com.example.houduan.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/list")
    public Map<String, Object> list(){
        Map<String, Object> data = new HashMap<>();
        List<Comment> comments = commentService.findAll();
        data.put("comments", comments);
        data.put("total", comments.size());
        return data;   // 返回所有评论信息
    }

   @DeleteMapping("/deleteById/{id}")
    public Integer deleteById(@PathVariable Integer id){
        return commentService.deleteById(id);  // 返回1表示删除了一条数据
    }

    @GetMapping("/page")
    public IPage<Comment> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String scenicArea,
                                   @RequestParam(defaultValue = "") String username){
        IPage<Comment> data = commentService.selectPage(pageNum, pageSize, scenicArea, username);
        return data;
    }

    @GetMapping("/selectById/{id}")
    public Comment selectById(@PathVariable Integer id){
        return commentService.getById(id);
    }

    @DeleteMapping("/deleteBatch/")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return commentService.deleteBatch(ids);
    }

    @PostMapping("/updateById/{id}")
    public Integer updateById(@PathVariable Integer id){
        return commentService.updateById(id);
    }

    @RequestMapping("/getData")
    public Map<String, Object> getData() throws JsonProcessingException {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String[] args = new String[]{"python", "E:\\毕业设计\\python情感分析\\情感分析\\topWords.py"};
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line = null;
            while ((line = in.readLine()) != null) {
                result.add(line);
            }
            System.out.println(result);
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();

//        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Tue", "Fri", "Sat", "Sun"));
//        map.put("y", CollUtil.newArrayList(150, 230, 225, 135, 147, 260, 218));

        String str = result.get(4);
        JSONObject jsonObj = new JSONObject(str);

        map.put("result", CollUtil.newArrayList(jsonObj));

        return map;
    }
}
