package com.dw.vod.starter.config;

import com.dw.vod.starter.controller.VodVideoController;
import com.dw.vod.starter.controller.impl.SyncVodController;
import com.dw.vod.starter.factory.AbstractFactory;
import com.dw.vod.starter.factory.VideoFactory;
import com.dw.vod.starter.properties.RedisProperties;
import com.dw.vod.starter.properties.VideoProperties;
import com.dw.vod.starter.service.VideoInfoService;
import com.dw.vod.starter.service.VideoService;
import com.dw.vod.starter.service.VideoUpLoader;
import com.dw.vod.starter.service.impl.AsyncVideoServiceImpl;
import com.dw.vod.starter.service.impl.DefaultVideoUpLoader;
import com.dw.vod.starter.service.impl.VideoInfoServiceImpl;
import com.dw.vod.starter.controller.impl.AsyncVodVodVideoController;
import com.dw.common.utils.RedisUtil;
import com.dw.vod.starter.utils.VideoTranscodeUtil;
import com.dw.vod.starter.utils.VideoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CuiYiXiang
 * @Classname VideoService
 * @Description TODO
 * @Date 2020/7/29
 */
@Configuration
@EnableConfigurationProperties({VideoProperties.class, RedisProperties.class})
@ConditionalOnProperty(
        prefix = "alicustom",
        name = "openvod",
        havingValue = "true"
)
public class VideoConfig {

    private static final Logger logger = LoggerFactory.getLogger(AsyncVideoServiceImpl.class);


    @Autowired
    private VideoProperties videoProperties;


    @Bean(name = "vodVideoFactory")
    public AbstractFactory vodVideoFactory() {
        VideoFactory videoFactory = new VideoFactory();
        logger.info("视频服务初始化完成");
        return videoFactory;
    }

    @Bean(name = "vodVideoUtils")
    public VideoUtils vodVideoUtils() {
        VideoUtils vodVideoUtils = new VideoUtils(videoProperties);
        logger.info("视频上传工具初始化完成");
        return vodVideoUtils;
    }

    @Bean(name = "vodVideoService")
    public VideoService vodVideoService(VideoProperties videoProperties) {
        VideoService vodVideoService = vodVideoFactory().createVideoService(videoProperties);
        logger.info("视频服务初始化完成");
        return vodVideoService;
    }

    @Bean(name = "vodVideoController")
    public VodVideoController vodVideoController(VideoProperties videoProperties) {
        VodVideoController vodVideoController = null;
        boolean syncUpload = videoProperties.isSyncUpload();
        if (syncUpload) {
//            同步上传控制器
            vodVideoController = new SyncVodController();
        } else {
//            异步上传控制器
            vodVideoController = new AsyncVodVodVideoController();
        }
        logger.info(syncUpload ? "同步上传控制器初始化完成" : "异步上传控制器初始化完成");
        return vodVideoController;
    }

    @Bean(name = "vodVideoController")
    public AsyncVodVodVideoController vodVideoController() {
        AsyncVodVodVideoController asyncVodVodVideoController = new AsyncVodVodVideoController();
        logger.info("视频服务接口初始化完成");
        return asyncVodVodVideoController;
    }

    @Bean(name = "vodRedisUtil")
    public RedisUtil vodRedisUtil() {
        RedisUtil redisUtil = new RedisUtil();
        logger.info("redis工具初始化完成");
        return redisUtil;
    }

    @Bean(name = "vodVideoUpLoader")
    public VideoUpLoader vodVideoUpLoader() {
        DefaultVideoUpLoader defaultVideoUpLoader = new DefaultVideoUpLoader();
        logger.info("视频上传器初始化完成");
        return defaultVideoUpLoader;
    }

    @Bean(name = "vodVideoTranscodeUtil")
    public VideoTranscodeUtil vodVideoTranscodeUtil() {
        VideoTranscodeUtil videoTranscodeUtil = new VideoTranscodeUtil();
        logger.info("视频编码转换工具初始化完成");
        return videoTranscodeUtil;
    }

    @Bean(name = "videoInfoService")
    public VideoInfoService videoInfoService(){
        VideoInfoServiceImpl videoInfoService = new VideoInfoServiceImpl();
        logger.info("视频信息服务初始化完成");
        return videoInfoService;
    }

}
