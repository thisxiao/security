package com.xjx.security.core.properties;

import com.xjx.security.core.social.configutils.SocialPropertiesCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author XJX
 * @Date 2020/7/14 17:30
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QQProperties extends SocialPropertiesCommon {

    private String providerId = "qq";
}
