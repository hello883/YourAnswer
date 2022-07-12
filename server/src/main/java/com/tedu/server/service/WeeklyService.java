package com.tedu.server.service;

import com.tedu.server.pojo.WeeklyPointVO;

import java.util.List;

public interface WeeklyService {
    public boolean add(Integer id,Integer num);

    public boolean refresh();

    public List<WeeklyPointVO> sortByWeeklyPoint();

    public boolean insert(Integer id);
}
