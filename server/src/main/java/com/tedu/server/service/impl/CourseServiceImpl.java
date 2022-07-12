package com.tedu.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.sun.org.apache.xerces.internal.xs.StringList;
import com.tedu.server.mapper.CourseMapper;
import com.tedu.server.mapper.PostMapper;
import com.tedu.server.mapper.TagAndServiceMapper;
import com.tedu.server.mapper.TagMapper;
import com.tedu.server.pojo.*;
import com.tedu.server.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    PostMapper postMapper;
    @Autowired
    TagAndServiceMapper tagAndServiceMapper;
    @Autowired
    TagMapper tagMapper;

    @Override
    public boolean insert(CourseInsertDTO courseInsertDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("number", courseInsertDTO.getNumber());
        CourseDAO courseDAO = courseMapper.selectOne(queryWrapper);

        if (courseDAO == null) {
            CourseDAO courseDAO1 = new CourseDAO();

            BeanUtils.copyProperties(courseInsertDTO,courseDAO1);
            int insertRow = courseMapper.insert(courseDAO1);
            return insertRow>=1?true:false;
        }
        throw new RuntimeException("该课程已存在，添加失败");
    }

    @Override
    public List<CourseDAO> selectByN(String s) {
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.like("name",s);
        List<CourseDAO> courseDAO1 = courseMapper.selectList(queryWrapper1);
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.like("number",s);
        List<CourseDAO> courseDAO2 = courseMapper.selectList(queryWrapper2);

        ArrayList<CourseDAO> courseList = new ArrayList<>();

        for (CourseDAO course:courseDAO1){
            courseList.add(course);
        }
        for (CourseDAO course:courseDAO2){
            courseList.add(course);
        }
        return courseList;

//        ArrayList<CourseVO> volist = new ArrayList<>();
//        for (CourseDAO course:courseDAO1){
//            CourseVO courseVO = new CourseVO();
//            BeanUtils.copyProperties(course,courseVO);
//            volist.add(courseVO);
//        }
//        for (CourseDAO course:courseDAO2){
//            CourseVO courseVO = new CourseVO();
//            BeanUtils.copyProperties(course,courseVO);
//            volist.add(courseVO);
//        }
//        return volist;
    }

//    @Override
//    public List<PostVO> getPostList(Integer id) {
//        //Q A
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("type_id",id);
//        queryWrapper.eq("type",'Q');
//        queryWrapper.eq("type",'A');
//        List<PostDAO> list = postMapper.selectList(queryWrapper);
//        ArrayList<PostVO> volist = new ArrayList<>();
//        for (PostDAO postDAO:list){
//            PostVO postVO = new PostVO();
//            BeanUtils.copyProperties(postDAO,postVO);
//            volist.add(postVO);
//        }
//        return volist;
//    }

    @Override
    public List<String> getTag(Integer id) {
        //多表查询
        //在tagandcourse表里通过course_ig查到tag_id
        //在tag表里通过tag_id查到TagDAO，返回List
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("course_id",id);
        List<TagAndCourseDAO> tagandcourselist = tagAndServiceMapper.selectList(queryWrapper1);

        ArrayList<Integer> tagIdList = new ArrayList<>();
        for (TagAndCourseDAO tagAndCourseDAO:tagandcourselist){
            tagIdList.add(tagAndCourseDAO.getTagId());
        }

        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.in("id",tagIdList);
        List<TagDAO> tagList = tagMapper.selectList(queryWrapper2);

        ArrayList<String> stringlist = new ArrayList<>();
        for (TagDAO tagDAO:tagList){
            stringlist.add(tagDAO.getName());
        }

        return stringlist;
    }

    @Override
    public List<CourseDAO> getAll() {
        List<CourseDAO> courseList = courseMapper.selectList(null);
        return courseList;
    }

    @Override
    public CourseDAO selectById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        CourseDAO courseDAO = courseMapper.selectOne(queryWrapper);
        return courseDAO;
    }
}
