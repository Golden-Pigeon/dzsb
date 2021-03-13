package com.xdu.dzsb.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wjy
 * @date: 2021/3/3
 * @Description: 微信小程序相关配置枚举类
 */
@Getter
@AllArgsConstructor
public enum WechatEnum {
    
    /**
     * 微信登录
     */
    WX_LOGIN("https://api.weixin.qq.com/sns/jscode2session"),
    
    /**
     * 获取access_token
     */
    WX_ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token"),
    
    /**
     * 获取二维码
     */
    WX_CODE("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="),
    
    /**
     * 小程序appid
     */
    APP_ID("wx66a6bcce5ea50751"),
    
    /**
     * 小程序secret
     */
    SECRET("399e146e1fb196b0c4fb71b6608f9c1e");
    
    private String value;
}