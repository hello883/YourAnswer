package com.tedu.server.pojo;

//根据用户id和课程id检测有无点赞收藏
public class UserPostDTO {
    Integer user_id;
    Integer post_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }
}
