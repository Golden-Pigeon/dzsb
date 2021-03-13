package com.xdu.dzsb.model.dto;

import com.xdu.dzsb.common.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description: 封装返回给前端的数据
 */
@Data
@AllArgsConstructor
public class ResultDTO {

    private Integer code;
    private String message;
    private Object data;

    public ResultDTO(ResultEnum result) {
        setResultEnum(result);
    }

    public void setResultEnum(ResultEnum result) {
        
        this.code = result.getCode();
        this.message = result.getMessage();
    }
}