package com.xjx.security.core.social.weixin.api;


/**
 * @Author XJX
 * @Date 2020/7/16 11:17
 * @Version 1.0
 */
public interface WeiXin {

    /**
     * 获得weixin用户信息
     * @return 用户信息
     */
    WeiXinUserInfo getUserInfo(String openid);
}
