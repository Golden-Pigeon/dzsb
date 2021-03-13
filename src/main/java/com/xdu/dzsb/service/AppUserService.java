package com.xdu.dzsb.service;

import com.xdu.dzsb.model.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;

public interface AppUserService {

    ResultDTO login(String userName, String password);

    ResultDTO register(String userName, String password);

    ResultDTO logout(String userName);

//    ResultDTO haveLogined(String userName);

}
