package com.xdu.dzsb.controller;

import com.xdu.dzsb.model.dto.ResultDTO;
import com.xdu.dzsb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultDTO login(@RequestParam String code,
                           @RequestParam String name,
                           @RequestParam String avatar) {
        return userService.login(code, name, avatar);
    
    }
    
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
