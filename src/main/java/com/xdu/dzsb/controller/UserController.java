package com.xdu.dzsb.controller;

import com.xdu.dzsb.model.dto.ResultDTO;
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
    public ResultDTO login(@RequestParam String code,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String avatar) {
        return userService.login(code, name, avatar);
    
    }

    @ApiOperation("获取用户信息")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户id(注意不是openId)", required = true, dataType = "String")
    )
    @GetMapping("/userInfo")
    @ResponseBody
    public ResultDTO buildRoom(@RequestParam(value = "userId") Integer userId) {
        
        return userService.getUserInfo(userId);
    }
    
    @PostMapping("/userInfo")
    @ResponseBody
    public ResultDTO buildRoom(@RequestParam Integer userId,
                               @RequestParam Integer sex,
                               @RequestParam Date birthday,
                               @RequestParam Integer bust,
                               @RequestParam Integer waistline,
                               @RequestParam Integer hipline,
                               @RequestParam String signature,
                               @RequestParam Integer height,
                               @RequestParam Integer weight) {
        
        return userService.updateUserInfo(userId, sex, birthday, bust, waistline, hipline, signature, height, weight);
    }
}
