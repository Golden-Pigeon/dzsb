package com.xdu.dzsb.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description: 变量配置枚举类
 */
@Getter
@AllArgsConstructor
public enum VariableEnum {

    /**
     * id无效
     */
    INVALID(-1),

    /**
     * 未删除/请求成功/用户第一次登录/设默认值
     */
    OK(0),

    /**
     * 已删除/用户非第一次登录
     */
    DELETE(1),
    
    /**
     * 登录超时时间-发布版(7天、单位为秒)
     */
    LOGIN_TIMEOUT(7 * 24 * 60 * 60),

    LOGIN_TIMEOUT_APP(24 * 60 * 60),
    
    /**
     * 登录超时时间-测试版(2分钟、单位为秒)
     */
    LOGIN_TIMEOUT_TEST(2 * 60);
    
    private Integer value;
}