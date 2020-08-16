package com.dw.img.starter.factory;

import com.dw.img.starter.properties.ImgProperties;
import com.dw.img.starter.service.ImgService;

/**
 * @author CuiYiXiang
 * @Classname AbstractImgFactory
 * @Description TODO
 * @Date 2020/8/2
 */
public interface AbstractImgFactory {
    ImgService createImgService(ImgProperties imgProperties);
}
