package com.xdu.dzsb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 */
@Data
@AllArgsConstructor
public class ExerciseInfoDTO {
    
    private String type;
    private Integer count;
    private Integer time;
}
