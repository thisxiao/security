package com.xjx.security.core.properties;

import lombok.Data;

/**
 * @Author XJX
 * @Date 2020/7/15 9:58
 * @Version 1.0
 */
@Data
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();
}
