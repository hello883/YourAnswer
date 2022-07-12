package com.tedu.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.server.mapper.LcMapper;
import com.tedu.server.mapper.PostMapper;
import com.tedu.server.mapper.UserMapper;
import com.tedu.server.pojo.*;
import com.tedu.server.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostMapper postMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    LcMapper lcMapper;

    @Override
    public boolean insertPost(PostInsertDTO postInsertDTO) {
        PostDAO postDAO = new PostDAO();
        BeanUtils.copyProperties(postInsertDTO, postDAO);
        postDAO.setCollectNum(0);
        postDAO.setLikeNum(0);
        postDAO.setTime(new Date());
        int insertRow = postMapper.insert(postDAO);
        return insertRow>=1?true:false;
    }

    //帖子详情页，显示帖子基本信息、发布者name、浏览者是已经点赞或收藏
    @Override
    public PostVO getPostInfo(UserPostDTO userPostDTO) {
        //根据postid得到postDAO
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("id", userPostDTO.getPost_id());
        PostDAO postDAO = postMapper.selectOne(queryWrapper1);
        //值赋给postVO
        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(postDAO, postVO);
        //获得发帖者name
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("id", postDAO.getOwnerId());
        UserDAO userDAO = userMapper.selectOne(queryWrapper2);
        postVO.setOwnerName(userDAO.getName());
        //有无点赞
        QueryWrapper queryWrapper3 = new QueryWrapper();
        queryWrapper3.eq("user_id",userPostDTO.getUser_id());
        queryWrapper3.eq("post_id",userPostDTO.getPost_id());
        queryWrapper3.eq("type",'L');
        LcDAO islike = lcMapper.selectOne(queryWrapper3);
        if (islike!=null){
            postVO.setL(true);
        }
        //有无收藏
        QueryWrapper queryWrapper4 = new QueryWrapper();
        queryWrapper4.eq("user_id",userPostDTO.getUser_id());
        queryWrapper4.eq("post_id",userPostDTO.getPost_id());
        queryWrapper4.eq("type",'C');
        LcDAO iscollect = lcMapper.selectOne(queryWrapper4);
        if (iscollect!=null){
            postVO.setC(true);
        }
        postVO.setCommentNum(getPostNum(userPostDTO.getPost_id()));

        return postVO;
    }

    @Override
    public boolean addLike(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        PostDAO postDAO = postMapper.selectOne(queryWrapper);
        postDAO.setLikeNum(postDAO.getLikeNum()+1);
        int updateRow = postMapper.update(postDAO, queryWrapper);

        return updateRow>=1?true:false;
    }

    @Override
    public boolean addCollect(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        PostDAO postDAO = postMapper.selectOne(queryWrapper);
        postDAO.setCollectNum(postDAO.getCollectNum()+1);
        int updateRow = postMapper.update(postDAO, queryWrapper);

        return updateRow>=1?true:false;
    }

    @Override
    public boolean cancelLike(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        PostDAO postDAO = postMapper.selectOne(queryWrapper);
        postDAO.setLikeNum(postDAO.getLikeNum()-1);
        int updateRow = postMapper.update(postDAO, queryWrapper);

        return updateRow>=1?true:false;
    }

    @Override
    public boolean cancelCollect(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        PostDAO postDAO = postMapper.selectOne(queryWrapper);
        postDAO.setCollectNum(postDAO.getCollectNum()-1);
        int updateRow = postMapper.update(postDAO, queryWrapper);

        return updateRow>=1?true:false;
    }

    //用户信息页显示该用户发过的帖子
    @Override
    public List<PostVO> getUserPost(Integer ownerId) {
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("owner_id", ownerId);
        List<PostDAO> daoList = postMapper.selectList(queryWrapper1);
        ArrayList<PostVO> voList = new ArrayList<>();
        for(PostDAO postDAO:daoList){
            PostVO postVO = new PostVO();
            BeanUtils.copyProperties(postDAO, postVO);
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("id", postDAO.getOwnerId());
            UserDAO userDAO = userMapper.selectOne(queryWrapper2);
            postVO.setOwnerName(userDAO.getName());
            voList.add(postVO);
        }
        return voList;
    }

    //VO id
    @Override
    public List<PostVO> getPostList(PostDTO postDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type_id", postDTO.getTypeId());
        Character type = postDTO.getType();
        if(type == '0'){
            queryWrapper.ne("type", '1');
            queryWrapper.ne("type", '2');
        } else{
            queryWrapper.eq("type", type);
        }
        List<PostDAO> daoList = postMapper.selectList(queryWrapper);
        ArrayList<PostVO> voList = new ArrayList<>();
        for(PostDAO postDAO:daoList){
            PostVO postVO = new PostVO();
            BeanUtils.copyProperties(postDAO, postVO);

            Integer userId = postDAO.getOwnerId();
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("id", userId);
            UserDAO userDAO = userMapper.selectOne(queryWrapper1);
            String ownerName = userDAO.getName();
            postVO.setOwnerName(ownerName);

            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("user_id", postDTO.getUserId());
            queryWrapper2.eq("post_id", postDAO.getId());
            queryWrapper2.eq("type", "L");
            LcDAO lcDAO = lcMapper.selectOne(queryWrapper2);
            postVO.setL(lcDAO!=null?true:false);

            QueryWrapper queryWrapper3 = new QueryWrapper();
            queryWrapper3.eq("user_id", postDTO.getUserId());
            queryWrapper3.eq("post_id", postDAO.getId());
            queryWrapper3.eq("type", "C");
            LcDAO lcDAO1 = lcMapper.selectOne(queryWrapper3);
            postVO.setC(lcDAO1!=null?true:false);

            QueryWrapper queryWrapper4 = new QueryWrapper();
            queryWrapper4.eq("type", '2');
            queryWrapper4.eq("type_id", postDAO.getId());
            List<PostDAO> commentList = postMapper.selectList(queryWrapper4);
            ArrayList<PostVO> coList = new ArrayList<>();

            for(PostDAO commentDAO:commentList){
                PostVO commVO = new PostVO();
                BeanUtils.copyProperties(commentDAO, commVO);
                QueryWrapper queryWrapper5 = new QueryWrapper();
                queryWrapper5.eq("id", commentDAO.getOwnerId());
                UserDAO userDAO1 = userMapper.selectOne(queryWrapper5);
                String ownerName1 = userDAO1.getName();
                commVO.setOwnerName(ownerName1);

                coList.add(commVO);
            }

            postVO.setList(coList);
            postVO.setState(false);
            postVO.setCommentNum(coList.size());

            voList.add(postVO);
        }
        return voList;
    }

    @Override
    public boolean deletePost(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        int deleteRow = postMapper.delete(queryWrapper);

        return deleteRow>=1?true:false;
    }

    //得到一级帖子的二级回复数量
    @Override
    public Integer getPostNum(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type",'1');
        queryWrapper.eq("type_id",id);
        Integer num = postMapper.selectCount(queryWrapper);

        return num;
    }

    @Override
    public List<PostVO> getAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<PostDAO> daoList = postMapper.selectList(queryWrapper);
        ArrayList<PostVO> voList = new ArrayList<>();
        for(PostDAO postDAO:daoList) {
            PostVO postVO = new PostVO();
            BeanUtils.copyProperties(postDAO, postVO);
            voList.add(postVO);
        }
        return voList;
    }


}