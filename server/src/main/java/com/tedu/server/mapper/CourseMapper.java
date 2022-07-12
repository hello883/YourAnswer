package com.tedu.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.server.pojo.CourseDAO;
import com.tedu.server.pojo.ServerResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper extends BaseMapper<CourseDAO> {
}
