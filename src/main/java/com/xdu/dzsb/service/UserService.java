package com.xdu.dzsb.service;

import com.xdu.dzsb.model.dto.ResultDTO;

import java.sql.Date;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 */
public interface UserService {
    
    ResultDTO login(String code, String avatarUrl);
    
    ResultDTO getUserInfo(String openid);
    
    ResultDTO updateUserInfo(String openid, String nickname, Integer sex, Date birthday, String avatarUrl, Double height, Double weight);

    ResultDTO updateGoal(String openid, Integer goal);

    ResultDTO check(String openid);

    ResultDTO clearAccomplished(String openid);
}
