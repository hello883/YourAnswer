package com.tedu.server.controller;

import com.tedu.server.pojo.CourseDAO;
import com.tedu.server.pojo.ServerResult;
import com.tedu.server.pojo.TagDAO;
import com.tedu.server.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping("tag/getAll")
    public ServerResult getAll() {
        List<TagDAO> tagList = tagService.getAll();
        return new ServerResult(0, "成功获取所有标签", tagList);
    }

    @RequestMapping("tag/searchCourse")
    public ServerResult searchCourse(Integer id){
        List<CourseDAO> courseDAO = tagService.searchCourse(id);
        return new ServerResult(0,"成功获取该标签下的所有课程",courseDAO);
    }

    @RequestMapping("tag/add")
    public ServerResult add(String name){
        boolean isSuccess = tagService.add(name);

        if (isSuccess){
            return new ServerResult(0,"添加标签成功",null);
        }else{
            return new ServerResult(301,"添加标签失败",null);
        }

    }
}
