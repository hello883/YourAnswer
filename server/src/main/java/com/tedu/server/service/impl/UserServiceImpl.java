package com.tedu.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.server.mapper.UserMapper;
import com.tedu.server.pojo.*;
import com.tedu.server.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public Integer register(UserAddDTO userAddDTO) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",userAddDTO.getName());

        UserDAO userDAO = userMapper.selectOne(queryWrapper);
        if(userDAO == null){
            //用户名不存在，继续注册
            //2、保存用户信息
            UserDAO insertuserDAO = new UserDAO();
            BeanUtils.copyProperties(userAddDTO,insertuserDAO);
            insertuserDAO.setPoint(0);
            int insertRow = userMapper.insert(insertuserDAO);
            if(insertRow==0){
                return -1;
            }
            else{
                //3、查询用户的ID
                //select * from user wher name=''and password=''
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.eq("name",userAddDTO.getName());
                UserDAO selectuserDAO = userMapper.selectOne(queryWrapper1);
                Integer id = selectuserDAO.getId();
                return id;
            }
        }
        return -2;
    }


    @Override
    public boolean changePassword(UserUpdateDTO userUpdateDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",userUpdateDTO.getName());
        queryWrapper.eq("password",userUpdateDTO.getOldPassword());

        UserDAO userDAO = new UserDAO();
        userDAO.setPassword(userUpdateDTO.getNewPassword());
        int updateRow = userMapper.update(userDAO,queryWrapper);

        return updateRow>=1?true:false;
    }

    @Override
    public Integer login(UserDTO userDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",userDTO.getName());
        queryWrapper.eq("password",userDTO.getPassword());

        UserDAO userDAO = userMapper.selectOne(queryWrapper);
        if(userDAO!=null){
            return userDAO.getId();
        }
        return -1;
    }

    @Override
    public UserVO getUserInfo(Integer id) {
        UserDAO userDAO = userMapper.selectById(id);
        if(userDAO == null)
        {
            throw new RuntimeException("没有查到用户信息");
            //加运行时异常，类就不用声明异常
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDAO, userVO);
//        userVO.setName(userDAO.getName());
//        userVO.setDepartment(userDAO.getDepartment());
//        userVO.setGrade(userDAO.getGrade());
//        userVO.setId(userDAO.getId());
//        userVO.setPicture(userDAO.getPicture());
        return userVO;
    }

    @Override
    public boolean changePicture(UserUpdateDTO userUpdateDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",userUpdateDTO.getId());

        UserDAO userDAO = new UserDAO();
        userDAO.setPicture(userUpdateDTO.getPicture());
        int updateRow = userMapper.update(userDAO,queryWrapper);
        return updateRow>=1?true:false;
    }

    @Override
    public boolean changename(UserUpdateDTO userUpdateDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",userUpdateDTO.getId());

        UserDAO userDAO = new UserDAO();
        userDAO.setName(userUpdateDTO.getName());
        int updateRow = userMapper.update(userDAO,queryWrapper);
        return updateRow>=1?true:false;
    }

    @Override
    public boolean addpoint(UserUpdateDTO userUpdateDTO,Integer num) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",userUpdateDTO.getId());

        UserDAO userDAO = new UserDAO();
        UserDAO userDAO1 = userMapper.selectById(userUpdateDTO.getId());
        userDAO.setPoint(userDAO1.getPoint()+num);
        int updateRow = userMapper.update(userDAO,queryWrapper);
        return updateRow>=1?true:false;
    }

    @Override
    public String getNameById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        UserDAO userDAO = userMapper.selectOne(queryWrapper);
        return userDAO.getName();
    }
}