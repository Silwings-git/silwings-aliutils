package com.silwings.vod.starter.service.impl;

import com.aliyuncs.vod.model.v20170321.GetVideoInfosResponse;
import com.silwings.vod.starter.pojo.vod.dto.VideoInfo;
import com.silwings.vod.starter.service.VideoInfoService;
import com.silwings.vod.starter.utils.VideoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * @author CuiYiXiang
 * @Classname VideoInfoServiceImpl
 * @Description 视频信息服务.用户可以在需要使用的地方通过注入的方式获取该类对象,进行视频操作
 * @Date 2020/8/2
 */
public class VideoInfoServiceImpl implements VideoInfoService {

    private static final Logger logger = LoggerFactory.getLogger(VideoInfoServiceImpl.class);

    @Autowired
    private VideoUtils vodVideoUtils;

    @Override
    public String getVideoPlayUrl(String videoId) {
        return vodVideoUtils.getUrl(videoId);
    }

    @Override
    public String getVideoCoverUrl(String videoId) {
        return vodVideoUtils.getVideoCoverUrl(videoId);
    }

    @Override
    public VideoInfo getVideoPlayUrlAndCover(String videoId) {
        return vodVideoUtils.getVideoPlayUrlAndCover(videoId);
    }

    /**
     * description: 根据视频id集合获取视频封面
     * version: 1.0
     * date: 2020/9/11 8:19
     * author: 崔益翔
     * @param videoIds
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     */
    @Override
    public HashMap<String, String> getVideoCoverUrlByList(List<String> videoIds) {
        GetVideoInfosResponse videoInfos = null;
        try {
            videoInfos = vodVideoUtils.getVideoInfos(videoIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("批量获取视频封面失败");
        }
        HashMap<String, String> videoIdCoverUrlMap = new HashMap<>(16);
        if (null==videoInfos){
            return videoIdCoverUrlMap;
        }
        if (videoInfos.getVideoList() != null && videoInfos.getVideoList().size() > 0) {
            for (GetVideoInfosResponse.Video video : videoInfos.getVideoList()) {
                String videoId = video.getVideoId();
                String coverURL = video.getCoverURL();
                videoIdCoverUrlMap.put(videoId, coverURL);
            }
        }
        return videoIdCoverUrlMap;
    }

    /**
     * description: 根据视频id获取播放凭证
     * version: 1.0
     * date: 2020/9/11 8:20
     * author: 崔益翔
     * @param videoId
     * @return java.lang.String
     */
    @Override
    public String getPlayAuth(String videoId){
        String videoPlayAuth = null;
        try {
            videoPlayAuth = vodVideoUtils.getVideoPlayAuth(videoId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取播放凭证失败");
        }
        return videoPlayAuth;
    }

    /**
     * description: 根据视频id删除视频
     * version: 1.0
     * date: 2020/9/11 8:20
     * author: 崔益翔
     * @param videoId
     * @return void
     */
    @Override
    public void delVideoByVideoId(String videoId){
        try {
            vodVideoUtils.deleteVideo(videoId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除视频失败");
            return;
        }
        logger.info("删除视频成功");
    }

    /**
     * description: 批量删除视频
     * version: 1.0
     * date: 2020/9/11 8:20
     * author: 崔益翔
     * @param videoIds
     * @return void
     */
    @Override
    public void delVideoByVideoId(List<String> videoIds){
        try {
            vodVideoUtils.deleteVideo(videoIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除视频失败");
            return;
        }
        logger.info("删除视频成功");
    }

}
