package com.tedu.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.server.mapper.CourseMapper;
import com.tedu.server.mapper.TagAndServiceMapper;
import com.tedu.server.mapper.TagMapper;
import com.tedu.server.pojo.CourseDAO;
import com.tedu.server.pojo.TagAndCourseDAO;
import com.tedu.server.pojo.TagDAO;
import com.tedu.server.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Autowired
    TagAndServiceMapper tagAndServiceMapper;
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<TagDAO> getAll() {
        List<TagDAO> tagList = tagMapper.selectList(null);
        return tagList;
    }

    @Override
    public List<CourseDAO> searchCourse(Integer id) {
        //多表查询
        //在tagandcourse表里通过tag_ig查到course_id
        //在course表里通过course_id查到courseDAO，生成对应的courseVO，返回List
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("tag_id",id);
        List<TagAndCourseDAO> tagandcourselist = tagAndServiceMapper.selectList(queryWrapper1);

        ArrayList<Integer> courseIdList = new ArrayList<>();
        for (TagAndCourseDAO tagAndCourseDAO:tagandcourselist){
            courseIdList.add(tagAndCourseDAO.getCourseId());
        }

        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.in("id",courseIdList);
        List<CourseDAO> courseList = courseMapper.selectList(queryWrapper2);

//        ArrayList<CourseVO> volist = new ArrayList<>();
//        for (Integer courseId:courseIdList){
//            QueryWrapper queryWrapper3 = new QueryWrapper();
//            queryWrapper3.eq("id", courseId);
//            CourseDAO courseDAO = courseMapper.selectOne(queryWrapper3);
//            CourseVO courseVO = new CourseVO();
//            BeanUtils.copyProperties(courseDAO,courseVO);
//            volist.add(courseVO);
//        }


        return courseList;
    }

    @Override
    public boolean add(String name) {
        //判断表里没有此标签
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",name);
        TagDAO tagDAO1 = tagMapper.selectOne(queryWrapper);

        if (tagDAO1 == null){
            TagDAO tagDAO = new TagDAO();
            tagDAO.setName(name);
            int insertRow = tagMapper.insert(tagDAO);

            return insertRow>=1?true:false;
        }
        throw new RuntimeException("添加的标签已存在");
    }
}
