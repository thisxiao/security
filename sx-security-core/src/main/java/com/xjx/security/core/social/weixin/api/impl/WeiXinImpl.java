package com.xjx.security.core.social.weixin.api.impl;

import com.alibaba.fastjson.JSON;
import com.xjx.security.core.social.weixin.api.WeiXin;
import com.xjx.security.core.social.weixin.api.WeiXinUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author XJX
 * @Date 2020/7/16 11:18
 * @Version 1.0
 * Weixin API调用模板， scope为Request的Spring bean, 根据当前用户的accessToken创建
 */
@Slf4j
public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {
    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    public WeiXinImpl(String accessToken){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return super.getMessageConverters();
    }

    /**
     * 获取微信用户信息
     * @param openid 用户唯一标识
     * @return 用户信息
     */
    @Override
    public WeiXinUserInfo getUserInfo(String openid) {
        String url = URL_GET_USER_INFO + openid;
        String response = getRestTemplate().getForObject(url, String.class);
        if(StringUtils.contains(response,"errcode")){
            log.error("获取用户信息失败：{}",response);
            throw new RuntimeException("获取用户信息失败:" + response);
        }
        WeiXinUserInfo userInfo = null;
        try{
            userInfo = JSON.parseObject(response, WeiXinUserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
