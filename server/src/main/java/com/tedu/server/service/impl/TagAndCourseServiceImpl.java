package com.tedu.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.server.mapper.TagAndServiceMapper;
import com.tedu.server.pojo.TagAndCourseDAO;
import com.tedu.server.service.TagAndCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagAndCourseServiceImpl implements TagAndCourseService {
    @Autowired
    TagAndServiceMapper tagAndServiceMapper;

    @Override
    public boolean add(TagAndCourseDAO tagAndCourseDAO) {
        //加一门课，课程号不能重复
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id", tagAndCourseDAO.getCourseId());
        List<TagAndCourseDAO> tagAndCourseDAO1 = tagAndServiceMapper.selectList(queryWrapper);

        if (tagAndCourseDAO1.size() == 0) {
            int insertRow = tagAndServiceMapper.insert(tagAndCourseDAO);
            return insertRow>=1?true:false;
        }
        throw new RuntimeException("添加课程已存在");
    }

}
