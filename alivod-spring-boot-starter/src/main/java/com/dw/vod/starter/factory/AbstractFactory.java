package com.dw.vod.starter.factory;

import com.dw.vod.starter.properties.VideoProperties;
import com.dw.vod.starter.service.VideoService;

/**
 * @author CuiYiXiang
 * @Classname AbstractFactory
 * @Description TODO
 * @Date 2020/7/30
 */
public interface AbstractFactory {
    VideoService createVideoService(VideoProperties videoProperties);
}
