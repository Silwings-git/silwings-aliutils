package com.silwings.img.starter.factory;

import com.silwings.img.starter.properties.ImgProperties;
import com.silwings.img.starter.service.ImgService;

/**
 * @author CuiYiXiang
 * @Classname AbstractImgFactory
 * @Description 图片服务抽象方法工厂
 * @Date 2020/8/2
 */
public interface AbstractImgFactory {
    /**
     * description: 根据ImgProperties创建图片服务
     * version: 1.0
     * date: 2020/9/11 7:51
     * author: 崔益翔
     * @param imgProperties  配置文件
     * @return com.silwings.img.starter.service.ImgService
     */
    ImgService createImgService(ImgProperties imgProperties);
}
