package com.tedu.server.service;

import com.tedu.server.pojo.UserAddDTO;
import com.tedu.server.pojo.UserDTO;
import com.tedu.server.pojo.UserUpdateDTO;
import com.tedu.server.pojo.UserVO;

public interface UserService {

    public Integer register(UserAddDTO userAddDTO);

    public boolean changePassword(UserUpdateDTO userUpdateDTO);

    public Integer login(UserDTO userDTO);

    public UserVO getUserInfo(Integer id);

    public boolean changePicture(UserUpdateDTO userUpdateDTO);

    public boolean changename(UserUpdateDTO userUpdateDTO);

    public boolean addpoint(UserUpdateDTO userUpdateDTO,Integer num);

    public String getNameById(Integer id);

}