package com.xjx.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author XJX
 * @Date 2020/7/15 14:32
 * @Version 1.0
 * Description：继承SpringSocialConfigurer为我们提供的SocialAuthenticationFilter的配置类SpringSocialConfigurer
 * 并重写其后置处理方法，让其默认拦截包含我们配置字符串的请求
 */
public class SxSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public SxSpringSocialConfigurer(String filterProcessesUrl){
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T postProcess(T object) {
        //在父类处理完SocialAuthenticationFilter之后的基础上，将其默认拦截url改成配置文件的值
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
