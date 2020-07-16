package com.xjx.security.core.social.qq.connet;

import com.xjx.security.core.social.qq.api.QQ;
import com.xjx.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Author XJX
 * @Date 2020/7/14 16:53
 * @Version 1.0
 */
public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 测试当前服务是否可以使用
     * @param api the qq
     * @return true/false
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    /**
     *
     * 将我们从QQ获取的用户信息设置成Connection对象相对应的字段信息
     * 记住：connection对象的字段是固定的
     * @param api the qq
     * @param values connection
     */
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);//个人主页url，QQ没有，这里设null
        values.setProviderUserId(userInfo.getOpenId());
    }

    /**
     * 同上面的方法一样也是通过api拿到一个标准的用户信息---》之后会讲
     * @param api the qq
     * @return 用户标准信息
     */
    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    /***
     * 微博等更新主页信息---这里不用管
     * @param api the qq
     * @param message 主页
     */
    @Override
    public void updateStatus(QQ api, String message) {
        //do noting
    }
}
