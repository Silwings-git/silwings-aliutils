package com.silwings.vod.starter.properties;


import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author CuiYiXiang
 * @Classname RedisProperties
 * @Description TODO
 * @Date 2020/7/31
 */
@ConfigurationProperties(prefix = "alicustom.redisprefix")
public class RedisProperties {
    /**
     * 获取文件上传状态时需要的常量:
     * author: 崔益翔
     */
    public static final String VIDEO_SOLE_ID = "ali:utils:videoSoleId:";

    /**
     * 获取文件上传成功后信息时需要的的常量
     * author: 崔益翔
     */
    public static final String VIDEO_DETAIL_SAVE_ID = "ali:utils:videoDetailSaveId:";

    private String videoSoleId;
    private String videoDetailSaveId;

    public String getVideoSoleId() {
        if (StringUtils.isEmpty(videoSoleId)) {
            return VIDEO_SOLE_ID;
        }
        return VIDEO_SOLE_ID + videoSoleId;
    }

    public void setVideoSoleId(String videoSoleId) {
        this.videoSoleId = videoSoleId;
    }

    public String getVideoDetailSaveId() {
        if (StringUtils.isEmpty(videoDetailSaveId)) {
            return VIDEO_DETAIL_SAVE_ID;
        }
        return VIDEO_DETAIL_SAVE_ID + videoDetailSaveId;
    }

    public void setVideoDetailSaveId(String videoDetailSaveId) {
        this.videoDetailSaveId = videoDetailSaveId;
    }
}
