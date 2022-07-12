package com.tedu.server.controller;

import com.tedu.server.pojo.ServerResult;
import com.tedu.server.pojo.WeeklyPointVO;
import com.tedu.server.service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class WeelkPointController {
    @Autowired
    WeeklyService weeklyService;

    @RequestMapping("/weeklypoint/add")
    public ServerResult add(Integer id, Integer num){
        boolean isSuccess = weeklyService.add(id,num);
        if (isSuccess) {
            return new ServerResult(0, "加分成功", null);
            //在大型项目中成功后应返回id,用于后续工作
        }
        else {
            return new ServerResult(303, "加分失败", null);
        }
    }

    @RequestMapping("/weeklypoint/sortByWeeklyPoint")
    public ServerResult sortByWeeklyPoint(){
        List<WeeklyPointVO> sortList = weeklyService.sortByWeeklyPoint();
        return new ServerResult(0, "排序成功", sortList);
    }


    @RequestMapping("/weeklypoint/refresh")
    public ServerResult refresh() {
        boolean isSuccess = weeklyService.refresh();
        if (isSuccess) {
            return new ServerResult(0, "更新成功", null);
            //在大型项目中成功后应返回id,用于后续工作
        }
        else {
            return new ServerResult(303, "更新失败", null);
        }
    }

    @RequestMapping("/weeklypoint/insert")
    public ServerResult insert(Integer id) {
        boolean isSuccess = weeklyService.insert(id);
        if (isSuccess) {
            return new ServerResult(0, "添加成功", null);
            //在大型项目中成功后应返回id,用于后续工作
        }
        else {
            return new ServerResult(303, "添加失败", null);
        }
    }
}