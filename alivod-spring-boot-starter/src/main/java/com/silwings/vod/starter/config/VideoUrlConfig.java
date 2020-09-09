package com.silwings.vod.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author CuiYiXiang
 * @Classname VideoUrlConfig
 * @Description TODO
 * @Date 2020/8/16
 */
@Configuration
@ConditionalOnProperty(
        prefix = "alicustom",
        name = "openvod",
        havingValue = "true"
)
public class VideoUrlConfig {
    public static final String URL = "${alicustom.vodurl}";
}
