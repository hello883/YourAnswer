package com.tedu.server.service;

import com.tedu.server.pojo.CourseDAO;
import com.tedu.server.pojo.CourseVO;
import com.tedu.server.pojo.TagDAO;

import java.util.List;

public interface TagService {
    /**
     * 返回所有标签
     * @return tagDAO
     */
    public List<TagDAO> getAll();

    /**
     * 找到相应标签下的所有课程
     * @param tagId
     * @return List<CourseVO>
     */
    public List<CourseDAO> searchCourse(Integer id);

    /**
     * 后台管理 增加标签
     * @param 标签name
     * @return boolean
     */
    public boolean add(String name);
}
