package com.dw.img.starter.config;

import com.dw.img.starter.properties.CompressLevelMappingProperties;
import com.dw.img.starter.service.ImgCompressService;
import com.dw.img.starter.service.impl.ImgCompressServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CuiYiXiang
 * @Classname ImgCompressConfig
 * @Description TODO
 * @Date 2020/8/2
 */
@Configuration
@EnableConfigurationProperties(CompressLevelMappingProperties.class)
@ConditionalOnProperty(
        prefix = "alicustom.aliimg",
        name = "compress",
        havingValue = "true"
)
public class ImgCompressConfig {

    private static final Logger logger = LoggerFactory.getLogger(ImgCompressConfig.class);

    @Bean(name = "ossImgCompressService")
    public ImgCompressService ossImgCompressService() {
        ImgCompressServiceImpl ossImgCompressService = new ImgCompressServiceImpl();
        logger.info("图片压缩服务初始化完成");
        return ossImgCompressService;
    }

}
