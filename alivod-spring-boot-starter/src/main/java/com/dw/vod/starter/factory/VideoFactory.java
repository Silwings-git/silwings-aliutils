package com.dw.vod.starter.factory;

import com.dw.vod.starter.properties.VideoProperties;
import com.dw.vod.starter.service.VideoService;
import com.dw.vod.starter.service.impl.AsyncVideoServiceImpl;
import com.dw.vod.starter.service.impl.SyncVideoServiceImpl;
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
