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
 * @Description TODO
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
