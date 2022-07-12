package com.tedu.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.server.mapper.UserMapper;
import com.tedu.server.mapper.WeeklyPointMapper;
import com.tedu.server.pojo.UserDAO;
import com.tedu.server.pojo.WeeklyPointDAO;
import com.tedu.server.pojo.WeeklyPointVO;
import com.tedu.server.service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class WeeklyServiceImpl implements WeeklyService {
    @Autowired
    WeeklyPointMapper weeklyPointMapper;

    @Override
    public boolean add(Integer id,Integer num) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("owner_id",id);
        WeeklyPointDAO weeklyPointDAO = weeklyPointMapper.selectOne(queryWrapper);
        weeklyPointDAO.setWeeklyPoint(weeklyPointDAO.getWeeklyPoint()+num);
        int row = weeklyPointMapper.update(weeklyPointDAO,queryWrapper);
        return row>=1?true:false;
    }

    @Override
    public boolean refresh() {
        boolean isUpdate=true;
        //SQL:update weekly_point set weekly_point=0
        List<WeeklyPointDAO> weeklyPointDAOS = weeklyPointMapper.selectList(null);

        for(WeeklyPointDAO dao:weeklyPointDAOS){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("owner_id",dao.getOwnerId());
            dao.setWeeklyPoint(0);
            int row = weeklyPointMapper.update(dao,queryWrapper);
            if(row<1){
                isUpdate=false;
                break;
            }
        }
        return isUpdate;
    }

    @Autowired
    UserMapper userMapper;

    @Override
    public List<WeeklyPointVO> sortByWeeklyPoint() {
        List<WeeklyPointDAO> daoListAll = weeklyPointMapper.selectList(null);
//        daoListAll.sort(Comparator.comparing(WeeklyPointDAO::getWeeklyPoint));
        Collections.sort(daoListAll, new Comparator<WeeklyPointDAO>() {
            @Override
            public int compare(WeeklyPointDAO o1, WeeklyPointDAO o2) {
                if(o1.getWeeklyPoint()>=o2.getWeeklyPoint()){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        ArrayList<WeeklyPointVO> sortList = new ArrayList<>();
        Integer ranking=1;
        for(WeeklyPointDAO weeklyPointDAO:daoListAll){
            WeeklyPointVO vo = new WeeklyPointVO();
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("ID",weeklyPointDAO.getOwnerId());
            UserDAO userDAO = userMapper.selectOne(queryWrapper);
            vo.setRanking(ranking);
            vo.setName(userDAO.getName());
            vo.setWeeklyPoint(weeklyPointDAO.getWeeklyPoint());
            sortList.add(vo);
            ranking = ranking+1;
        }
        return sortList;
    }

    @Override
    public boolean insert(Integer id) {
        WeeklyPointDAO weeklyPointDAO = new WeeklyPointDAO();
        weeklyPointDAO.setOwnerId(id);
        weeklyPointDAO.setWeeklyPoint(0);
        int row = weeklyPointMapper.insert(weeklyPointDAO);

        return row>=1?true:false;
    }
}
