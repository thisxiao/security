package com.xjx.security.core.social.weixin.connet;

import com.xjx.security.core.social.weixin.api.WeiXin;
import com.xjx.security.core.social.weixin.api.WeiXinUserInfo;
import lombok.NoArgsConstructor;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Author XJX
 * @Date 2020/7/16 14:23
 * @Version 1.0
 */
@NoArgsConstructor
public class WeiXinAdapter implements ApiAdapter<WeiXin> {

    private String openid;

    public WeiXinAdapter(String openid){
        this.openid = openid;
    }

    /**
     * 测试当前服务是否可以使用
     * @param api the weixin
     * @return true/false
     */
    @Override
    public boolean test(WeiXin api) {
        return true;
    }

    /**
     *
     * 将我们从weixin获取的用户信息设置成Connection对象相对应的字段信息
     * 记住：connection对象的字段是固定的
     * @param api the weixin
     * @param values connection
     */
    @Override
    public void setConnectionValues(WeiXin api, ConnectionValues values) {
        WeiXinUserInfo userInfo = api.getUserInfo(openid);
        values.setProviderUserId(userInfo.getOpenid());
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getHeadimgurl());
    }

    /**
     * 同上面的方法一样也是通过api拿到一个标准的用户信息---》之后会讲
     * @param api the weixin
     * @return 用户标准信息
     */
    @Override
    public UserProfile fetchUserProfile(WeiXin api) {
        return null;
    }

    /***
     * 微博等更新主页信息---这里不用管
     * @param api the weixin
     * @param message 主页
     */
    @Override
    public void updateStatus(WeiXin api, String message) {
        //do noting
    }
}
