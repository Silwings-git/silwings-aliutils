package com.dw.vod.starter.pojo.vod.dto;

import java.io.Serializable;

/**
 * @author CuiYiXiang
 * @Classname VideoMessageDto
 * @Description TODO
 * @Date 2020/7/1
 */
public class VideoMessageDto implements Serializable {


    /**
     * 上传中:
     * <p>
     * author: 崔益翔
     */
    public static final String VIDEO_UPLOAD_WAIT = "0";
    /**
     * 上传成功:
     * <p>
     * author: 崔益翔
     */
    public static final String VIDEO_UPLOAD_SUCCESS = "1";
    /**
     * 上传失败:
     * <p>
     * author: 崔益翔
     */
    public static final String VIDEO_UPLOAD_FAIL = "2";


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

    /**
     * 播放凭证
     */
    private String playAuth;
    /**
     * 系统生成的文件名
     */
    private String fileNameId;
    /**
     * 用户本地文件名
     */
    private String fileName;

    /**
     * 视频大小
     */
    private Long size;

    /**
     * 返回消息
     */
    private String msg;


    /**
     * 视频标题
     */
    private String title;

    public VideoMessageDto() {
    }

    public VideoMessageDto(String fileNameId, String title) {
        this.fileNameId = fileNameId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public VideoMessageDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public VideoMessageDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public VideoMessageDto setSize(Long size) {
        this.size = size;
        return this;
    }

    public VideoMessageDto setFileNameId(String fileNameId) {
        this.fileNameId = fileNameId;
        return this;
    }

    /**
     * description:  上传状态，使用常量声明，0：上传中，1：上传成功，2：上传失败
     * author: 崔益翔
     */
    private String upStatus;

    public VideoMessageDto setUpStatus(String upStatus) {
        this.upStatus = upStatus;
        return this;
    }

    public VideoMessageDto setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * description: 设置云端视频id信息
     * version: 1.0
     * date: 2020/7/2 8:13
     * author: 崔益翔
     *
     * @param videoId 云端视频id
     * @return com.dw.cloud.dto.VideoMessageDto
     */
    public VideoMessageDto setVideoId(String videoId) {
        this.videoId = videoId;
        return this;
    }

    /**
     * description: 设置云端视频url信息
     * version: 1.0
     * date: 2020/7/2 8:13
     * author: 崔益翔
     *
     * @param url 云端视频url
     * @return com.dw.cloud.dto.VideoMessageDto
     */
    public VideoMessageDto setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * description: 设置云端视频凭证信息
     * version: 1.0
     * date: 2020/7/2 8:13
     * author: 崔益翔
     *
     * @param playAuth 云端视频凭证
     * @return com.dw.cloud.dto.VideoMessageDto
     */
    public VideoMessageDto setPlayAuth(String playAuth) {
        this.playAuth = playAuth;
        return this;
    }

    public VideoMessageDto setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getUrl() {
        return url;
    }

    public String getPlayAuth() {
        return playAuth;
    }

    public String getFileNameId() {
        return fileNameId;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getSize() {
        return size;
    }

    public String getMsg() {
        return msg;
    }

    public String getUpStatus() {
        return upStatus;
    }
}
