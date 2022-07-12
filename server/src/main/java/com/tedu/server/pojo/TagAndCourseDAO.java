package com.tedu.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tag_course")
public class TagAndCourseDAO {
    //表tag_course：tag_id、course_id
    Integer tagId;
    Integer courseId;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
