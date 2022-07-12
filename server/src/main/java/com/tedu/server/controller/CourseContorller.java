package com.tedu.server.controller;

import com.tedu.server.pojo.*;
import com.tedu.server.service.CourseService;
import com.tedu.server.service.TagAndCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
public class CourseContorller {
    @Autowired
    CourseService courseService;
    @Autowired
    TagAndCourseService tagAndCourseService;

    @RequestMapping("course/insert")
    public ServerResult insert(CourseInsertDTO courseInsertDTO){
        boolean isSuccess = courseService.insert(courseInsertDTO);
        if (isSuccess){
            return new ServerResult(0,"添加课程成功",null);
        }else{
            return new ServerResult(301,"添加课程失败",null);
        }

    }

    @RequestMapping("course/selectByN")
    public ServerResult selectByN(String s){
        List<CourseDAO> courseList = courseService.selectByN(s);
        if (courseList != null){
            return new ServerResult(0,"成功",courseList);
        }else{
            return new ServerResult(303,"失败",null);
        }
    }

    @RequestMapping("course/selectById")
    public ServerResult selectById(Integer id){
        CourseDAO courseDAO = courseService.selectById(id);
        if (courseDAO != null){
            return new ServerResult(0,"成功",courseDAO);
        }else{
            return new ServerResult(304,"失败",null);
        }
    }

//    @RequestMapping("course/getPostList")
//    public ServerResult getPostList(Integer id){
//        List<PostVO> postList = courseService.getPostList(id);
//        if (postList != null){
//            return new ServerResult(0,"成功获得帖子",postList);
//        }else{
//            return new ServerResult(303,"目前该课程无帖子",null);
//        }
//    }

    @RequestMapping("course/getTag")
    public ServerResult getTag(Integer id){
        List<String> tag = courseService.getTag(id);
        return new ServerResult(0,"成功获得标签",tag);
    }

    @RequestMapping("course/getAll")
    public ServerResult getAll(){
        List<CourseDAO> courseList = courseService.getAll();
        return new ServerResult(0,"成功返回所有课程",courseList);
    }

}
