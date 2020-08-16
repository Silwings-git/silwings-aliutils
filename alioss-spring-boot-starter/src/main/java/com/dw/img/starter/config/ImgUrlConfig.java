package com.dw.img.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author CuiYiXiang
 * @Classname ImgUrlConfig
 * @Description TODO
 * @Date 2020/8/16
 */
@Configuration
@ConditionalOnProperty(
        prefix = "alicustom",
        name = "openimg",
        havingValue = "true"
)
public class ImgUrlConfig {
    public static final String URL = "${alicustom.imgurl}";
}
