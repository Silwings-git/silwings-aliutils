package com.silwings.vod.starter.factory;

import com.silwings.vod.starter.properties.VideoProperties;
import com.silwings.vod.starter.service.VideoService;

/**
 * @author CuiYiXiang
 * @Classname AbstractFactory
 * @Description TODO
 * @Date 2020/7/30
 */
public interface AbstractFactory {
    VideoService createVideoService(VideoProperties videoProperties);
}
