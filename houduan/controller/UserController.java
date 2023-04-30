package com.example.houduan.controller;

import cn.hutool.core.collection.CollUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

@RestController
public class UserController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/add")
    public int add(){
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("H:\\demo02.py");

        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        int a = 5, b = 10;
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj = pyFunction.__call__(new PyInteger(a), new PyInteger(b));

//        System.out.println("the anwser is: " + pyobj);

        return Integer.parseInt(String.valueOf(pyobj));
    }

    @RequestMapping("/getMap")
    public Map<String, Object> getMap(){
        ArrayList<String> result = new ArrayList<String>();
        try {
            String[] args = new String[]{"python", "E:\\毕业设计\\python情感分析\\情感分析\\topWords.py"};
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
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

        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Tue", "Fri", "Sat", "Sun"));
        map.put("y", CollUtil.newArrayList(150, 230, 225, 135, 147, 260, 218));

        return map;
    }


}
