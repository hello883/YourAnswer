package com.tedu.server.pojo;


import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("likeandcollect")
public class LcDAO {
    Integer userId;
    Integer postId;
    Character type;
    Date time;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
