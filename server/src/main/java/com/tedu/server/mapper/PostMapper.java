package com.tedu.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.server.pojo.PostDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper extends BaseMapper<PostDAO> {
}
