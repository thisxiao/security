package com.xjx.security.core.social.qq.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjx.security.core.social.qq.api.QQ;
import com.xjx.security.core.social.qq.api.QQUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @Author XJX
 * @Date 2020/7/14 15:40
 * @Version 1.0
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    /**
     * 获取用户openid路径
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户信息路径
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    /**
     * 用户appId
     */
    private String appId;

    /**
     * 用户openId
     */
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken,String appId){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(url,String.class);

        System.out.println(result);

        this.openId = StringUtils.substringBetween(result,"\"openid\":\"","\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO,appId,openId);
        String result = getRestTemplate().getForObject(url,String.class);
        if(result == null){
            throw new RuntimeException("获取用户信息失败");
        }

        log.info(result);
        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败",e);
        }
    }
}
