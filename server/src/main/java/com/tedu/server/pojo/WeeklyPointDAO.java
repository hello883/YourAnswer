package com.tedu.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("weekly_point")
public class WeeklyPointDAO {

    Integer ownerId;
    Integer weeklyPoint;

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getWeeklyPoint() {
        return weeklyPoint;
    }

    public void setWeeklyPoint(Integer weeklyPoint) {
        this.weeklyPoint = weeklyPoint;
    }
}

