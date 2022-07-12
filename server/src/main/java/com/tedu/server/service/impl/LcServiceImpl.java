package com.tedu.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.server.mapper.LcMapper;
import com.tedu.server.mapper.PostMapper;
import com.tedu.server.pojo.LcDAO;
import com.tedu.server.pojo.LcDTO;
import com.tedu.server.pojo.PostDAO;
import com.tedu.server.pojo.PostVO;
import com.tedu.server.service.LcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class LcServiceImpl implements LcService {
    @Autowired
    LcMapper lcMapper;

    @Autowired
    PostMapper postMapper;

    @Override
    public boolean addLc(LcDTO lcDTO) {
        LcDAO lcDAO = new LcDAO();
        BeanUtils.copyProperties(lcDTO, lcDAO);
        lcDAO.setTime(new Date());
        int insertRow = lcMapper.insert(lcDAO);
        return insertRow>=1?true:false;
    }

    @Override
    public boolean cancelLc(LcDTO lcDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", lcDTO.getUserId());
        queryWrapper.eq("post_id", lcDTO.getPostId());
        queryWrapper.eq("type", lcDTO.getType());
        int deleteRow = lcMapper.delete(queryWrapper);
        return deleteRow>=1?true:false;
    }

    @Override
    public Character LC(LcDTO lcDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", lcDTO.getUserId());
        queryWrapper.eq("post_id", lcDTO.getPostId());
        queryWrapper.eq("type", lcDTO.getType());
        LcDAO lcDAO = lcMapper.selectOne(queryWrapper);
        if(lcDAO != null){
            cancelLc(lcDTO);
            return 'c';
        }else{
            addLc(lcDTO);
            return 'a';
        }
    }

    @Override
    public List<PostVO> userCollect(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("type", "C");
        List<LcDAO> daoList = lcMapper.selectList(queryWrapper);
        ArrayList<PostVO> voList = new ArrayList<>();
        for(LcDAO lcDAO:daoList){
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("id", lcDAO.getPostId());
            PostDAO postDAO = postMapper.selectOne(queryWrapper1);
            PostVO postVO = new PostVO();
            BeanUtils.copyProperties(postDAO, postVO);
            voList.add(postVO);
        }
        return voList;
    }
}
