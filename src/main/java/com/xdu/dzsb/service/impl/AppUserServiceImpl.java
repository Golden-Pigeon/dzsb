package com.xdu.dzsb.service.impl;

import com.xdu.dzsb.common.enums.ResultEnum;
import com.xdu.dzsb.common.enums.VariableEnum;
import com.xdu.dzsb.model.dto.ResultDTO;
import com.xdu.dzsb.model.entity.AppUser;
import com.xdu.dzsb.repository.AppUserRepository;
import com.xdu.dzsb.service.AppUserService;
import com.xdu.dzsb.service.base.RedisOperator;
import io.netty.util.internal.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    RedisOperator redisOperator;

    @Override
    public ResultDTO login(String userName, String password) {
        //TODO: 解决重复登录问题
//        if(redisOperator.hasKey(userName))
//            return new ResultDTO(ResultEnum.USER_LOGINED);
        AppUser appUser = appUserRepository.findAppUserByUserName(userName);
        if(appUser == null || !appUser.getPassword().equals(password)){
            return new ResultDTO(ResultEnum.WRONG_USER_NAME_OR_PASSWORD);
        }
        int rand = (int)(Math.random() * 10000);
        String session = String.valueOf(userName.hashCode() * password.hashCode() * rand);//获取随机session
        redisOperator.set(userName, session, VariableEnum.LOGIN_TIMEOUT_APP.getValue());//session存入缓存
        Map<String, Object> data = new HashMap<>();
        data.put("session", session);
        ResultDTO result = new ResultDTO(ResultEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    @Override
    public ResultDTO register(String userName, String password){
        AppUser appUser = appUserRepository.findAppUserByUserName(userName);
        if(!(appUser == null))
            return new ResultDTO(ResultEnum.USERNAME_EXISTED);
        AppUser newUser = new AppUser(userName, password);
        appUserRepository.save(newUser);
        return new ResultDTO(ResultEnum.SUCCESS);
    }

    @Override
    public ResultDTO logout(String username){
        if(!redisOperator.hasKey(username))
            return new ResultDTO(ResultEnum.NOT_LOGIN);
        redisOperator.deleteByKey(username);
        return new ResultDTO(ResultEnum.SUCCESS);
    }

//    @Override
//    public ResultDTO haveLogined(String userName){
//        ResultDTO result = new ResultDTO(ResultEnum.SUCCESS);
//        if(redisOperator.hasKey(userName))
//            result.setData("true");
//        else
//            result.setData("false");
//        return result;
//    }
}
