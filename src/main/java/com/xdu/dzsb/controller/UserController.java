package com.xdu.dzsb.controller;

import com.xdu.dzsb.model.dto.ResultDTO;
import com.xdu.dzsb.service.HistoryService;
import com.xdu.dzsb.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 *
 */
@RestController
@RequestMapping("/user")
@Api(value = "小程序用户API", description = "登录、获取/修改用户信息及测试")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private HistoryService historyService;

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public String test(){
        return "test";
    }


    @PostMapping("/login")
    @ResponseBody
    @ApiOperation("小程序登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "code", value = "小程序登录时获取的用户code", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "用户昵称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "avatar", value = "用户头像地址", required = false, dataType = "String")
    })
    public ResultDTO login(@RequestParam String code, @RequestParam String avatarUrl) {
        return userService.login(code, avatarUrl);
    
    }

    @ApiOperation("获取用户信息")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户id(注意不是openId)", required = true, dataType = "String")
    )
    @GetMapping("/user_info")
    @ResponseBody
    public ResultDTO buildRoom(@RequestParam String openid) {
        
        return userService.getUserInfo(openid);
    }
    
    @PostMapping("/user_info_update")
    @ResponseBody
    public ResultDTO buildRoom(@RequestParam String openid,
                               @RequestParam Integer sex,
                               @RequestParam Date birthday,
                               @RequestParam String avatarUrl,
                               @RequestParam Double height,
                               @RequestParam Double weight,
                               @RequestParam String nickname) {
        return userService.updateUserInfo(openid, nickname, sex, birthday, avatarUrl, height, weight);
    }

    @PostMapping("/set_goal")
    public ResultDTO setGoal(@RequestParam String openid, @RequestParam Integer goal) {
        return userService.updateGoal(openid, goal);
    }

    @PostMapping("/check")
    public ResultDTO check(@RequestParam String openid){
        return userService.check(openid);
    }

    @PostMapping("/clear_accomplished")
    public ResultDTO clearAccomplished(@RequestParam String openid){
        return userService.clearAccomplished(openid);
    }

    @PostMapping("/get_history/brief")
    public ResultDTO getBriefHistory(@RequestParam String openid){
        return historyService.getBriefHistories(openid);
    }

    @PostMapping("/get_history/detailed")
    public ResultDTO getDetailedHistories(@RequestParam Integer id){
        return historyService.getDetailedHistory(id);
    }
}
