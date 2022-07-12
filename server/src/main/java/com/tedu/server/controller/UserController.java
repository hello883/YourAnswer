package com.tedu.server.controller;

import com.tedu.server.pojo.*;
import com.tedu.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/user/register")
    public ServerResult register(UserAddDTO userAddDTO){
        Integer userId = userService.register(userAddDTO);
        if(userId==-1){
            return new ServerResult(303,"注册失败",null);
        }
        else if(userId==-2){
            return new ServerResult(404,"用户名已存在",null);
        }
        else{
            return new ServerResult(0,"注册成功",userId);
        }
    }

    @RequestMapping("/user/changePassword")
    public ServerResult changePassword(UserUpdateDTO userUpdateDTO){
        boolean isSuccess = userService.changePassword(userUpdateDTO);
        if(isSuccess){
            return new ServerResult(0,"修改密码成功",null);
        }
        else{
            return new ServerResult(101,"原始密码或用户名错误，修改密码失败",null);

        }
    }

    @RequestMapping("/user/login")
    public ServerResult login(UserDTO userDTO){
        Integer userId = userService.login(userDTO);
        if(userId>=1){
            return new ServerResult(0,"登录成功",userId);
        }
        else{
            return new ServerResult(101,"密码错误，登录失败",null);
        }
    }

    @RequestMapping("/user/getUserInfo")
    public ServerResult getUserInfo(Integer id){
        UserVO userVO = userService.getUserInfo(id);
        return new ServerResult(0,"查询成功",userVO);
    }

    @RequestMapping("/user/changePicture")
    public ServerResult changePicture(UserUpdateDTO userUpdateDTO){
        boolean isSuccess = userService.changePicture(userUpdateDTO);
        if(isSuccess){
            return new ServerResult(0,"头像修改成功",null);
        }
        else{
            return new ServerResult(101,"头像修改失败",null);

        }
    }

    @RequestMapping("/user/changename")
    public ServerResult changename(UserUpdateDTO userUpdateDTO){
        boolean isSuccess = userService.changename(userUpdateDTO);
        if(isSuccess){
            return new ServerResult(0,"用户名修改成功",null);
        }
        else{
            return new ServerResult(101,"用户名修改失败",null);
        }
    }

    @RequestMapping("/user/addpoint")
    public ServerResult addpoint(UserUpdateDTO userUpdateDTO,Integer num){
        boolean isSuccess = userService.addpoint(userUpdateDTO,num);
        if(isSuccess){
            return new ServerResult(0,"积分添加成功",null);
        }
        else{
            return new ServerResult(101,"积分添加失败",null);
        }
    }

    @RequestMapping("/user/getNameById")
    public  ServerResult getNameById(Integer id){
        String nameById = userService.getNameById(id);
        return new ServerResult(0,"获取用户名成功",nameById);
    }
}