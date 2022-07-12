package com.tedu.server.service;

import com.tedu.server.pojo.CourseDAO;
import com.tedu.server.pojo.CourseInsertDTO;
import com.tedu.server.pojo.CourseVO;
import com.tedu.server.pojo.PostVO;

import java.util.List;

public interface CourseService {
    //insert(courseInsertDTO): boolean，添加课程
    //selectByN(text: String): List<CourseVO>，String为name或number，用于搜索框
    //getPostList(courseId: Integer): List<PostVO>，用到PostMapper: 得到课程下的帖子
    //getTag(id: Integer): List<String>，用到TagAndCourseMapper、TagMapper 多表查询:在tagandcourse里通过course_id查到tag_ig，在tag表里通过tag_id查到tagDAO，生成对应的nameList，用于显示课程的标签
    public boolean insert(CourseInsertDTO courseInsertDTO);
    public List<CourseDAO> selectByN(String s);
    //public List<PostVO> getPostList(Integer id);
    public List<String> getTag(Integer id);
    public List<CourseDAO> getAll();
    public CourseDAO selectById(Integer id);
}
