package com.xjx.security.core.validate.code.image;

import com.xjx.security.core.validate.code.processor.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * 图片验证码处理器
 * @Author XJX
 * @Date 2020/7/13 15:45
 * @Version 1.0
 */
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * 发送图形验证码将其写在响应中
     * @param request 请求
     * @param validateCode 验证码
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws IOException {
        ImageIO.write(validateCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }

}
