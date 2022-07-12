package com.tedu.server.service;

import com.tedu.server.pojo.LcDTO;
import com.tedu.server.pojo.PostVO;

import java.util.List;

public interface LcService {
    public boolean addLc(LcDTO lcDTO);
    public boolean cancelLc(LcDTO lcDTO);
    public Character LC(LcDTO lcDTO);
    public List<PostVO> userCollect(Integer userId);
}
