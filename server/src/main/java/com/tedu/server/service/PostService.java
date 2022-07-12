package com.tedu.server.service;

import com.tedu.server.pojo.PostInsertDTO;
import com.tedu.server.pojo.PostVO;
import com.tedu.server.pojo.PostDTO;
import com.tedu.server.pojo.UserPostDTO;

import java.util.List;


public interface PostService {
    boolean insertPost(PostInsertDTO postInsertDTO);
    PostVO getPostInfo(UserPostDTO userPostDTO);
    boolean addLike(Integer id);
    boolean addCollect(Integer id);
    boolean cancelLike(Integer id);
    boolean cancelCollect(Integer id);
    List<PostVO> getUserPost(Integer ownerId);
    List<PostVO> getPostList(PostDTO postDTO);
    boolean deletePost(Integer id);
    Integer getPostNum(Integer id);
    List<PostVO> getAll();
}
