package com.xjx.security.core.validate.code.image;

import com.xjx.security.core.validate.code.ValidateCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author XJX
 * @Date 2020/7/10 16:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCode extends ValidateCode {

    private static final long serialVersionUID = 8650488029029209693L;

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        super(code, expireTime);
        this.image = image;
    }

}
