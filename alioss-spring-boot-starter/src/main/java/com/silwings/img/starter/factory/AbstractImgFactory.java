package com.silwings.img.starter.factory;

import com.silwings.img.starter.properties.ImgProperties;
import com.silwings.img.starter.service.ImgService;

/**
 * @author CuiYiXiang
 * @Classname AbstractImgFactory
 * @Description TODO
 * @Date 2020/8/2
 */
public interface AbstractImgFactory {
    ImgService createImgService(ImgProperties imgProperties);
}
