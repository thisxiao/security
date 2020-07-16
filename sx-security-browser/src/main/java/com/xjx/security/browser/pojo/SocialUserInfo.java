package com.xjx.security.browser.pojo;

import lombok.Data;

/**
 * @Author XJX
 * @Date 2020/7/15 16:51
 * @Version 1.0
 */
@Data
public class SocialUserInfo {
    /**提供商唯一标识*/
    private String providerId;

    /**用户在提供商的唯一标识（其实就是openId）*/
    private String providerUserId;

    /**用户在提供商的昵称*/
    private String nickName;

    /**用户在提供商的头像*/
    private String headImg;
}
