package com.silwings.vod.starter.factory;

import com.silwings.vod.starter.properties.VideoProperties;
import com.silwings.vod.starter.service.VideoService;
import com.silwings.vod.starter.service.impl.AsyncVideoServiceImpl;
import com.silwings.vod.starter.service.impl.SyncVideoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CuiYiXiang
 * @Classname VideoFactory
 * @Description TODO
 * @Date 2020/7/30
 */
public class VideoFactory implements AbstractFactory {

    private static final Logger logger = LoggerFactory.getLogger(VideoFactory.class);


    /**
     * description: 根据配置文件构建不同视频服务
     * version: 1.0
     * date: 2020/9/11 8:12
     * author: 崔益翔
     * @param videoProperties
     * @return com.silwings.vod.starter.service.VideoService
     */
    @Override
    public VideoService createVideoService(VideoProperties videoProperties) {
//        工具不同参数创建不同的VideoService
        VideoService videoService = null;
        boolean syncUpload = videoProperties.isSyncUpload();
        if (syncUpload) {
//            同步上传
            videoService = new SyncVideoServiceImpl();
        } else {
//            异步上传
            videoService = new AsyncVideoServiceImpl();
        }
        logger.info(syncUpload ? "同步上传服务初始化完成" : "异步上传服务初始化完成");
        return videoService;
    }
}
