package com.silwings.vod.starter.pojo.vod.dto;

/**
 * @author CuiYiXiang
 * @Classname VideoInfo
 * @Description TODO
 * @Date 2020/8/2
 */
public class VideoInfo {

    /**
     * 封面图片
     */
    private String coverUrl;

    /**
     * 视频id
     */
    private String videoId;
    /**
     * 视频url
     */
    private String url;

    public String getCoverUrl() {
        return coverUrl;
    }

    public VideoInfo setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    public String getVideoId() {
        return videoId;
    }

    public VideoInfo setVideoId(String videoId) {
        this.videoId = videoId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public VideoInfo setUrl(String url) {
        this.url = url;
        return this;
    }
}
