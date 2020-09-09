package com.silwings.vod.starter.service;

import com.silwings.vod.starter.pojo.vod.dto.VideoInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @author CuiYiXiang
 * @Classname VideoInfoService
 * 视频信息服务
 * @Description TODO
 * @Date 2020/8/2
 */
public interface VideoInfoService {
    /**
     * description: getVideoPlayUrl
     * 获取视频播放地址
     * version: 1.0
     * date: 2020/8/2 21:57
     * author: 崔益翔
     * @param videoid
     * @return java.lang.String
     */
    String getVideoPlayUrl(String videoid);

    /**
     * description: getVideoCoverUrl
     * 获取视频封面
     * version: 1.0
     * date: 2020/8/2 21:57
     * author: 崔益翔
     * @param videoId
     * @return java.lang.String
     */
    String getVideoCoverUrl(String videoId);

    /**
     * description: getVideoPlayUrlAndCover
     * 获取视频播放地址和封面
     * version: 1.0
     * date: 2020/8/2 21:57
     * author: 崔益翔
     * @param videoId
     * @return VideoInfo
     */
    VideoInfo getVideoPlayUrlAndCover(String videoId);

    /**
     * description: getVideoCoverUrlByList
     * 批量获取视频封面
     * version: 1.0
     * date: 2020/8/2 21:57
     * author: 崔益翔
     * @param videoIds
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     */
    HashMap<String, String> getVideoCoverUrlByList(List<String> videoIds);

    /**
     * description: getPlayAuth
     * 获取播放凭证
     * version: 1.0
     * date: 2020/8/2 21:59
     * author: 崔益翔
     * @param videoId
     * @return java.lang.String
     */
    String getPlayAuth(String videoId);

    /**
     * description: delVideoByVideoId
     * 根据视频id删除视频
     * version: 1.0
     * date: 2020/8/2 22:05
     * author: 崔益翔
     * @param videoId
     * @return void
     */
    void delVideoByVideoId(String videoId);

    /**
     * description: delVideoByVideoId
     * 批量删除视频
     * version: 1.0
     * date: 2020/8/2 22:06
     * author: 崔益翔
     * @param videoIds
     * @return void
     */
    void delVideoByVideoId(List<String> videoIds);
}
