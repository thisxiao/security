package com.xjx.security.core.properties;

/**
 * @Author XJX
 * @Date 2020/7/13 15:20
 * @Version 1.0
 */
public interface SecurityConstants {

    /**
     * 默认的处理验证码的url前缀
     */
    static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 当请求需要身份认证时，默认跳转的url
     *  @see SecurityController
     */
    static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 默认的用户名密码登录请求处理url
     */
    static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
    /**
     * 默认的手机验证码登录请求处理url
     */
    static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";
    /**
     * 默认登录页面
     *
     * @see SecurityController
     */
    static final String DEFAULT_LOGIN_PAGE_URL = "/sx-signIn.html";
    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
    /**
     * session失效默认的跳转地址
     */
    static final String DEFAULT_SESSION_INVALID_URL = "/session/invalid";

    /**
     * 获取第三方账号的用户信息对应的url
     */
    String DEFAULT_GET_SOCIAL_USERINFO_URL = "/social/user";
    /**
     * 默认注册页面
     */
    String DEFAULT_SIGNUP_URL = "/sx-signUp.html";
}
