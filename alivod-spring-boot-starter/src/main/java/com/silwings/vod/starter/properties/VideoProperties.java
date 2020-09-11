package com.silwings.vod.starter.properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author CuiYiXiang
 * @Classname VideoProperties
 * @Description 视频基本配置
 * @Date 2020/7/29
 */
@ConfigurationProperties(prefix = "alicustom.alivideo")
public class VideoProperties {

    /**
     * 阿里accessKeyId
     */
    private String accessKeyId;

    /**
     * 阿里accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 视频最大长度,单位秒
     */
    private String maxLength;

    /**
     * 开启同步上传,默认使用异步上传
     */
    private boolean syncUpload = false;

    /**
     * 上传进度打印
     */
    private boolean printProgress = false;

    /**
     * 统一视频后缀
     */
    private String videoSuffix = ".mp4";

    public String getVideoSuffix() {
        return videoSuffix;
    }

    /**
     * description: 获取统一后缀名
     * 返回不包含"."的后缀名,视频文件编码转换时需要
     * version: 1.0
     * date: 2020/8/1 21:11
     * author: 崔益翔
     *
     * @param
     * @return java.lang.String
     */
    public String getUnifyVideoSuffix() {
        return videoSuffix.substring(videoSuffix.lastIndexOf(".") + 1);
    }

    public void setVideoSuffix(String videoSuffix) {
        this.videoSuffix = videoSuffix;
    }

    public boolean isSyncUpload() {
        return syncUpload;
    }

    public void setSyncUpload(boolean syncUpload) {
        this.syncUpload = syncUpload;
    }

    public boolean isPrintProgress() {
        return printProgress;
    }

    public void setPrintProgress(boolean printProgress) {
        this.printProgress = printProgress;
    }

    public Long getMaxLength() {
        if (StringUtils.isEmpty(maxLength)) {
            return null;
        } else {
            Long length = null;
            try {
                length = Long.valueOf(maxLength);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
            return length;
        }
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public String toString() {
        return "VideoProperties{" +
                "accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", maxLength='" + maxLength + '\'' +
                '}';
    }
}
