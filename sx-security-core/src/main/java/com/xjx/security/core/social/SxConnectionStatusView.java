package com.xjx.security.core.social;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author XJX
 * @Date 2020/7/16 17:11
 * @Version 1.0
 */
@Component("connect/status")
public class SxConnectionStatusView extends AbstractView {


    @SuppressWarnings("unchecked")
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, List<Connection<?>>> connections = (Map<String, List<Connection<?>>>) map.get("connectionMap");

        Map<String,Boolean> result = new HashMap<>();

        for(String key:connections.keySet()){
            result.put(key, CollectionUtils.isNotEmpty(connections.get(key)));
        }

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
