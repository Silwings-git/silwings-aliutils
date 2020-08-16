package com.dw.img.starter.factory;

import com.dw.img.starter.properties.ImgProperties;
import com.dw.img.starter.service.ImgService;
import com.dw.img.starter.service.impl.NoCompressImgServiceImpl;
import com.dw.img.starter.service.impl.UseCompressImgServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CuiYiXiang
 * @Classname OssImgFactory
 * @Description TODO
 * @Date 2020/8/2
 */
public class OssImgFactory implements AbstractImgFactory {

    private static final Logger logger = LoggerFactory.getLogger(OssImgFactory.class);

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
