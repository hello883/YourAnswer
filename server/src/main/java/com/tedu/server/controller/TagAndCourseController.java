package com.tedu.server.controller;

import com.tedu.server.pojo.ServerResult;
import com.tedu.server.pojo.TagAndCourseDAO;
import com.tedu.server.service.TagAndCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class TagAndCourseController {
    @Autowired
    TagAndCourseService tagAndCourseService;

    @RequestMapping("tagandcourse/add")
    public ServerResult add(TagAndCourseDAO tagAndCourseDAO){
        boolean isSuccess = tagAndCourseService.add(tagAndCourseDAO);
        if (isSuccess){
            return new ServerResult(0,"添加课程-标签成功",null);
        }else{
            return new ServerResult(301,"添加课程-标签失败",null);
        }
    }
}
