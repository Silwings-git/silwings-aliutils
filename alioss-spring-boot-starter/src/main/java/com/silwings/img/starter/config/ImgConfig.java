package com.silwings.img.starter.config;

import com.silwings.img.starter.controller.OssImgController;
import com.silwings.img.starter.controller.impl.OssImgControllerImpl;
import com.silwings.img.starter.factory.AbstractImgFactory;
import com.silwings.img.starter.factory.OssImgFactory;
import com.silwings.img.starter.properties.ImgProperties;
import com.silwings.img.starter.service.ImgInfoService;
import com.silwings.img.starter.service.ImgService;
import com.silwings.img.starter.service.ImgUpLoader;
import com.silwings.img.starter.service.impl.ImgInfoServiceImpl;
import com.silwings.img.starter.service.impl.ImgUpLoaderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CuiYiXiang
 * @Classname ImgConfig
 * @Description TODO
 * @Date 2020/8/2
 */
@Configuration
@EnableConfigurationProperties(ImgProperties.class)
@ConditionalOnProperty(
        prefix = "alicustom",
        name = "openimg",
        havingValue = "true"
)
public class ImgConfig {

    private static final Logger logger = LoggerFactory.getLogger(ImgConfig.class);

    @Autowired
    private ImgProperties imgProperties;

    @Bean(name = "ossImgUpLoader")
    public ImgUpLoader ossImgUpLoader(){
        ImgUpLoaderImpl ossImgUpLoader = new ImgUpLoaderImpl();
        logger.info("OSS图片上传器初始化完成...");
        return ossImgUpLoader;
    }

    @Bean(name = "ossImgFactory")
    public AbstractImgFactory ossImgFactory(){
        AbstractImgFactory ossImgFactory = new OssImgFactory();
        logger.info("图片服务工厂初始化完成");
        return ossImgFactory;
    }

    @Bean(name = "ossImgService")
    public ImgService ossImgService(){
        ImgService ossImgService = ossImgFactory().createImgService(imgProperties);
        logger.info("图片服务初始化完成");
        return ossImgService;
    }

    @Bean(name = "ossImgController")
    public OssImgController ossImgController(){
        OssImgControllerImpl ossImgController = new OssImgControllerImpl();
        logger.info("图片上传控制器初始化完成");
        return ossImgController;
    }

    @Bean(name = "imgInfoService")
    public ImgInfoService imgInfoService(){
        ImgInfoServiceImpl imgInfoService = new ImgInfoServiceImpl();
        logger.info("图片信息服务初始化完成");
        return imgInfoService;
    }
}
