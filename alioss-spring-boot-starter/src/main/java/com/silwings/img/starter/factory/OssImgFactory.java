package com.silwings.img.starter.factory;

import com.silwings.img.starter.properties.ImgProperties;
import com.silwings.img.starter.service.ImgService;
import com.silwings.img.starter.service.impl.NoCompressImgServiceImpl;
import com.silwings.img.starter.service.impl.UseCompressImgServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CuiYiXiang
 * @Classname OssImgFactory
 * @Description 图片抽象方法工厂实现
 * @Date 2020/8/2
 */
public class OssImgFactory implements AbstractImgFactory {

    private static final Logger logger = LoggerFactory.getLogger(OssImgFactory.class);

    /**
     * description: 根据配置创建图片服务
     * version: 1.0
     * date: 2020/9/11 7:52
     * author: 崔益翔
     * @param imgProperties 配置文件
     * @return com.silwings.img.starter.service.ImgService
     */
    @Override
    public ImgService createImgService(ImgProperties imgProperties) {
        boolean compress = imgProperties.isCompress();
        ImgService imgService = null;
        if (compress) {
//            压缩
            imgService = new UseCompressImgServiceImpl();
        } else {
//            不压缩
            imgService = new NoCompressImgServiceImpl();
        }
        logger.info(compress ? "图片压缩上传服务初始化完成" : "图片上传服务初始化完成");
        return imgService;
    }
}
