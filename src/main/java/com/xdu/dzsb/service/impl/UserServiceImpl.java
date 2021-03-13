package com.xdu.dzsb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xdu.dzsb.common.enums.ResultEnum;
import com.xdu.dzsb.common.enums.VariableEnum;
import com.xdu.dzsb.common.enums.WechatEnum;
import com.xdu.dzsb.common.utils.HttpClientUtil;
import com.xdu.dzsb.model.dto.ExerciseInfoDTO;
import com.xdu.dzsb.model.dto.ResultDTO;
import com.xdu.dzsb.model.dto.UserInfoDTO;
import com.xdu.dzsb.model.entity.User;
import com.xdu.dzsb.repository.UserRepository;
import com.xdu.dzsb.service.UserService;
import com.xdu.dzsb.service.base.RedisOperator;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

/**
 * @author: wjy
 * @date: 2021/3/2
 * @description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    
    @Autowired
    private RedisOperator redisOperator;
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public ResultDTO login(String code, String name, String avatar) {
        Map<String, String> param = new HashMap<>();
        param.put("appid", WechatEnum.APP_ID.getValue());
        param.put("secret", WechatEnum.SECRET.getValue());
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        //向微信官方服务器发起请求以获得session_key和openid
        String wxResult = HttpClientUtil.doGet(WechatEnum.WX_LOGIN.getValue(), param);
        log.info("wxResult:{}", wxResult);
        //对Json数据进行解析
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        String openid = jsonObject.getString("openid");
        String session_key = jsonObject.getString("session_key");
        String session = UUID.randomUUID().toString().replace("-", " ").toUpperCase();
        if (StringUtil.isNullOrEmpty(openid)) {
            log.error("errcode:{}，errmsg: {}", jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
            return new ResultDTO(ResultEnum.INTERFACE_FAIL);
        }
        Map<String, Object> res = new HashMap<>();
        Optional<User> user = userRepository.findByOpenid(openid);
        if (user.isPresent()) {
            //用户已经登陆过
            userRepository.updateNameAndAvatar(user.get().getId(), name, avatar);
            redisOperator.set(session, session_key, VariableEnum.LOGIN_TIMEOUT.getValue());
            res.put("id", user.get().getId());
        } else {
            //用户未登录过
            User newUser = new User(openid, name, avatar);
            userRepository.save(newUser);
            redisOperator.set(session, session_key, VariableEnum.LOGIN_TIMEOUT.getValue());
            res.put("id", newUser.getId());
        }
        res.put("session", session);
        res.put("openId", openid);
        ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
        resultDTO.setData(res);
        return resultDTO;
    }
    
    @Override
    public ResultDTO getUserInfo(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        ResultDTO resultDTO = null;
        if (user.isPresent()) {
            User u = user.get();
            resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            List<ExerciseInfoDTO> detail = new ArrayList<>();
            UserInfoDTO userInfoDTO = new UserInfoDTO(u.getId(), u.getName(), u.getSex(), u.getBirthday()
            , u.getBust(), u.getWaistline(), u.getHipline(), u.getAvatar(), u.getSignature()
            , u.getHeight(), u.getWeight(), u.getCount(), u.getEnergy(), u.getTotalTime()
            , u.getTotalExerciseDays(), u.getContinueExerciseDays(), detail);
            resultDTO.setData(userInfoDTO);
        }
        else {
            resultDTO = new ResultDTO(ResultEnum.ID_INVALID);
        }
        return resultDTO;
    }
    
    @Override
    public ResultDTO updateUserInfo(Integer userId, Integer sex, Date birthday, Integer bust, Integer waistline, Integer hipline, String signature, Integer height, Integer weight) {
        return null;
    }
}
