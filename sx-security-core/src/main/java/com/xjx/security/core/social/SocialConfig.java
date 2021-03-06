package com.xjx.security.core.social;

import com.xjx.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * @Author XJX
 * @Date 2020/7/14 17:05
 * @Version 1.0
 * UsersConnectionRepository的实现类，用来拿着Connection对象查找UserConnection表中是否与之相对应的userId
 * userId就是我们系统中的唯一标识，这个应该由各个系统自己根据业务去指定，比如说你系统里的username是唯一的，
 * 则这个useId就可以是username
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Resource
    private DataSource dataSource;

    @Resource
    private SecurityProperties securityProperties;

    /**
     * 并不一定所有的系统都会在用户进行第三方登陆时“偷偷地”给用户注册一个新用户
     * 所以这里需要标明required = false
     */
    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        /*
         * 第二个参数的作用：根据条件查找该用哪个ConnectionFactory来构建Connection对象
         * 第三个参数的作用: 对插入到userConnection表中的数据进行加密和解密
         */
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        //设置userConnection表的前缀
        repository.setTablePrefix("sx_");
        if (connectionSignUp != null) {
            //如果有spring容器里connectionSignUp这个bean时，将其注入到UsersConnectionRepository
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    /**
     * 通过apply()该实例，可以将SocialAuthenticationFilter加入到spring-security的过滤器链
     */
    @Bean
    public SpringSocialConfigurer sxSocialSecurityConfig(){
        // 默认配置类，进行组件的组装
        // 包括了过滤器SocialAuthenticationFilter 添加到security过滤链中
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        SxSpringSocialConfigurer configurer = new SxSpringSocialConfigurer(filterProcessesUrl);
        // 设置注册页面路径要从配置文件里面读
        configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return configurer;
    }


    /**
     * ProviderSignInUtils有两个作用：
     * （1）从session里获取封装了第三方用户信息的Connection对象
     * （2）将注册的用户信息与第三方用户信息（QQ信息）建立关系并将该关系插入到userconnection表里
     * <p>
     * 需要的两个参数：
     * （1）ConnectionFactoryLocator 可以直接注册进来，用来定位ConnectionFactory
     * （2）UsersConnectionRepository----》getUsersConnectionRepository(connectionFactoryLocator)
     * 即我们配置的用来处理userconnection表的bean
     *
     * @param connectionFactoryLocator the connectionFactoryLocator
     * @return ProviderSignInUtils
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
    }
}
