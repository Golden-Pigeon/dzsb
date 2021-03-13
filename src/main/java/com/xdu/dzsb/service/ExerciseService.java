package com.xdu.dzsb.service;

import com.xdu.dzsb.model.dto.ResultDTO;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 */
public interface ExerciseService {
    
    ResultDTO upload(Integer type, String sport, String movie, String picture);
}
