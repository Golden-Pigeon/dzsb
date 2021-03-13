package com.xdu.dzsb.controller;

import com.xdu.dzsb.model.dto.ResultDTO;
import com.xdu.dzsb.model.entity.AppUser;
import com.xdu.dzsb.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/user")
public class AppUserController {
    @Autowired
    AppUserService appUserService;
    @PostMapping("/login")
    ResultDTO login(@RequestParam String userName, @RequestParam String password){
        return appUserService.login(userName, password);
    }

    @PostMapping("/logout")
    ResultDTO logout(@RequestParam String userName){
        return appUserService.logout(userName);
    }

    @PostMapping("/register")
    ResultDTO register(@RequestParam String userName, @RequestParam String password){
        return appUserService.register(userName, password);
    }
}
