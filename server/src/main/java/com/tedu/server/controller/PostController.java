package com.tedu.server.controller;

import com.tedu.server.pojo.*;
import com.tedu.server.service.PostService;
import com.tedu.server.service.UserService;
import com.tedu.server.service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    WeeklyService weeklyService;

    @RequestMapping("/post/insertPost")
    public ServerResult insertPost(PostInsertDTO postInsertDTO){
        boolean isSuccess = postService.insertPost(postInsertDTO);
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setId(postInsertDTO.getOwnerId());
        userService.addpoint(userUpdateDTO,2);
        weeklyService.add(postInsertDTO.getOwnerId(),2);

        if(isSuccess)
            return new ServerResult(0, "发布成功", null);
        else
            return new ServerResult(106, "发布失败", null);
    }

    @RequestMapping("/post/getPostInfo")
    public ServerResult getPostInfo(UserPostDTO userPostDTO) {
        PostVO postInfo = postService.getPostInfo(userPostDTO);
        if(postInfo != null)
            return new ServerResult(0, "获取成功", postInfo);
        else
            return new ServerResult(107, "信息缺失", null);
    }

    @RequestMapping("/post/getUserPost")
    public ServerResult getUserPost(Integer ownerId){
        List<PostVO> userPost = postService.getUserPost(ownerId);
        return new ServerResult(0, "获取成功", userPost);
    }

    @RequestMapping("/post/getPostList")
    public  ServerResult getPostList(PostDTO postDTO){
        List<PostVO> postList = postService.getPostList(postDTO);
        return new ServerResult(0, "获取成功", postList);
    }

    @RequestMapping("post/deletePost")
    public ServerResult deletePost(Integer id){
        boolean isSuccess = postService.deletePost(id);
        if(isSuccess)
            return new ServerResult(0, "删除帖子成功", null);
        else
            return new ServerResult(106, "删除帖子失败", null);
    }

    @RequestMapping("post/getPostNum")
    public ServerResult getPostNum(Integer id){
        Integer postNum = postService.getPostNum(id);
        return new ServerResult(0,"成功返回一级帖子的二级回复数",postNum);
    }

    @RequestMapping("post/getAll")
    public ServerResult getAll(){
        List<PostVO> all = postService.getAll();
        return new ServerResult(0, "获取成功", all);
    }

}
