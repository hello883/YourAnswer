package com.tedu.server.service;

import com.tedu.server.pojo.TagAndCourseDAO;

public interface TagAndCourseService {
     /**
     * 添加课程时需要添加一条记录
     * @param tagAndCourseDAO
     * @return boolean
     */
    public boolean add(TagAndCourseDAO tagAndCourseDAO);
}
