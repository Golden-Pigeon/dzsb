package com.xdu.dzsb.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description: 返回结果枚举类
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    
    /**
     * 正常返回(官方)
     */
    SUCCESS(200, "正常返回"),



    /**
     * 微信接口请求失败
     */
    INTERFACE_FAIL(20000, "微信接口请求失败"),
    
    /**
     * 用户id无效
     */
    ID_INVALID(20001, "用户id无效"),

    UPLOAD_FAILED(20002, "上传失败"),

    WRONG_USER_NAME_OR_PASSWORD(20003, "用户名或密码错误"),

    NOT_LOGIN(20004, "用户未登录"),

    /**
     * 更新失败
     */
    UPDATE_FAIL(20005, "更新失败"),
    
    /**
     * 删除失败
     */
    DELETE_FAIL(20006, "删除失败"),

    USERNAME_EXISTED(20007, "用户名已存在"),

    USER_LOGINED(20008, "用户名已登录"),

    VIDEO_PLAY_EXCEPTION(20009, "视频播放出现异常");

    private final Integer code;
    
    private final String message;
}