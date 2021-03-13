package com.xdu.dzsb.service;

import com.xdu.dzsb.model.dto.ResultDTO;

import java.sql.Date;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 */
public interface UserService {
    
    ResultDTO login(String code, String name, String avatar);
    
    ResultDTO getUserInfo(Integer userId);
    
    ResultDTO updateUserInfo(Integer userId, Integer sex, Date birthday, Integer bust, Integer waistline, Integer hipline, String signature, Integer height, Integer weight);
}
