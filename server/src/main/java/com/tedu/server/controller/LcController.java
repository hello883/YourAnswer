package com.tedu.server.controller;

import com.tedu.server.pojo.LcDTO;
import com.tedu.server.pojo.PostVO;
import com.tedu.server.pojo.ServerResult;
import com.tedu.server.service.LcService;
import com.tedu.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class LcController {
    @Autowired
    LcService lcService;

    @Autowired
    PostService postService;

    @RequestMapping("/lc/Lc")
    public  ServerResult Lc(LcDTO lcDTO){
        Character lc = lcService.LC(lcDTO);
        if(lc == 'c'){
            if(lcDTO.getType() == 'L')
                postService.cancelLike(lcDTO.getPostId());
            else if(lcDTO.getType() == 'C')
                postService.cancelCollect(lcDTO.getPostId());
            return new ServerResult(0, "取消点赞/收藏成功", null);
        }
        else{
            if(lcDTO.getType() == 'L')
                postService.addLike(lcDTO.getPostId());
            else if(lcDTO.getType() == 'C')
                postService.addCollect(lcDTO.getPostId());
            return new ServerResult(0, "点赞/收藏成功", null);
        }
    }

    @RequestMapping("/lc/addLc")
    public ServerResult addLc(LcDTO lcDTO){
        boolean isSuccess = lcService.addLc(lcDTO);
        if(lcDTO.getType() == 'L')
            postService.addLike(lcDTO.getPostId());
        else if(lcDTO.getType() == 'C')
            postService.addCollect(lcDTO.getPostId());
        if(isSuccess)
            return new ServerResult(0, "点赞/收藏成功", null);
        else
            return new ServerResult(104, "点赞/收藏失败", null);

    }

    @RequestMapping("/lc/cancelLc")
    public  ServerResult cancelLc(LcDTO lcDTO){
        boolean isSuccess = lcService.cancelLc(lcDTO);
        if(lcDTO.getType() == 'L')
            postService.cancelLike(lcDTO.getPostId());
        else if(lcDTO.getType() == 'C')
            postService.cancelCollect(lcDTO.getPostId());
        if(isSuccess)
            return new ServerResult(0, "取消点赞/收藏成功", null);
        else
            return new ServerResult(105, "取消点赞/收藏失败", null);
    }

    @RequestMapping("/lc/userCollect")
    public  ServerResult userCollect(Integer userId){
        List<PostVO> postVO = lcService.userCollect(userId);
        return new ServerResult(0, "获取成功", postVO);
    }
}
