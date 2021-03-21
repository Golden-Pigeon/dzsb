package com.xdu.dzsb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xdu.dzsb.common.enums.ResultEnum;
import com.xdu.dzsb.common.enums.VariableEnum;
import com.xdu.dzsb.common.enums.WechatEnum;
import com.xdu.dzsb.common.utils.HttpClientUtil;
import com.xdu.dzsb.common.utils.MathUtil;
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
    public ResultDTO login(String code, String avatarUrl) {
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
            userRepository.updateAvatarUrl(user.get().getOpenid(), avatarUrl);
            redisOperator.set(session, session_key, VariableEnum.LOGIN_TIMEOUT.getValue());
            res.put("id", user.get().getOpenid());
        } else {
            //用户未登录过
            User newUser = new User(openid);
            userRepository.save(newUser);
            redisOperator.set(session, session_key, VariableEnum.LOGIN_TIMEOUT.getValue());
            res.put("openid", newUser.getOpenid());
        }
        res.put("session", session);
        res.put("openId", openid);
        ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
        resultDTO.setData(res);
        return resultDTO;
    }
    
    @Override
    public ResultDTO getUserInfo(String openId) {
        Optional<User> user = userRepository.findByOpenid(openId);
        ResultDTO resultDTO = null;
        if (user.isPresent()) {
            User u = user.get();
            resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            List<ExerciseInfoDTO> detail = new ArrayList<>();
            UserInfoDTO userInfoDTO = new UserInfoDTO(u.getOpenid(), u.getNickname(), u.getSex(), u.getBirthday()
            , u.getAvatarUrl(), u.getHeight(), u.getWeight(), u.getGoalDays(), u.getAccomplishedDays()
            , u.getTotalExerciseDays(), u.getContinueExerciseDays(), detail, u.getLastCheckDate());
            resultDTO.setData(userInfoDTO);
        }
        else {
            resultDTO = new ResultDTO(ResultEnum.ID_INVALID);
        }
        return resultDTO;
    }
    
    @Override
    public ResultDTO updateUserInfo(String openid, String nickname, Integer sex, Date birthday, String avatarUrl, Double height, Double weight) {
        Optional<User> user = userRepository.findByOpenid(openid);
        if(!user.isPresent())
            return new ResultDTO(ResultEnum.ID_INVALID);
        User u = user.get();
        if(nickname != null)
            u.setNickname(nickname);
        if(sex != null)
            u.setSex(sex);
        if(birthday != null)
            u.setBirthday(birthday);
        if(avatarUrl != null)
            u.setAvatarUrl(avatarUrl);
        if(height != null)
            u.setHeight(height);
        if(weight != null)
            u.setWeight(weight);
        userRepository.save(u);
        return new ResultDTO(ResultEnum.SUCCESS);
    }

    @Override
    public ResultDTO updateGoal(String openid, Integer goal) {
        if(goal == null)
            return new ResultDTO(ResultEnum.SUCCESS);
        Optional<User> user = userRepository.findByOpenid(openid);
        if(!user.isPresent())
            return new ResultDTO(ResultEnum.ID_INVALID);
        User u = user.get();
        u.setGoalDays(goal);
        userRepository.save(u);
        return new ResultDTO(ResultEnum.SUCCESS);
    }

    @Override
    public ResultDTO check(String openid) {
        Optional<User> user = userRepository.findByOpenid(openid);
        if(!user.isPresent())
            return new ResultDTO(ResultEnum.ID_INVALID);
        User u = user.get();
        if(u.getAccomplishedDays() > u.getGoalDays())
            return new ResultDTO(ResultEnum.GOAL_ACCOMPLISHED);


        if(u.getLastCheckDate() == null) {
            u.setAccomplishedDays(1);
            u.setLastCheckDate(new Date(System.currentTimeMillis()));
        }
        else{
            Date today = new Date(System.currentTimeMillis());
            Date last = u.getLastCheckDate();
            if(MathUtil.differentDays(last, today) == 0)
                return new ResultDTO(ResultEnum.HAVE_CHECKED);
            if(MathUtil.differentDays(last, today) == 1){
                u.setLastCheckDate(today);
                u.setContinueExerciseDays(u.getContinueExerciseDays() + 1);
            } else {
                u.setLastCheckDate(today);
                u.setContinueExerciseDays(1);
            }

        }

        u.setAccomplishedDays(u.getAccomplishedDays() + 1);
        u.setTotalExerciseDays(u.getTotalExerciseDays() + 1);
        userRepository.save(u);
        return new ResultDTO(ResultEnum.SUCCESS);
    }

    @Override
    public ResultDTO clearAccomplished(String openid) {
        Optional<User> user = userRepository.findByOpenid(openid);
        if(!user.isPresent())
            return new ResultDTO(ResultEnum.ID_INVALID);
        User u = user.get();
        u.setAccomplishedDays(0);
        userRepository.save(u);
        return new ResultDTO(ResultEnum.SUCCESS);
    }
}
