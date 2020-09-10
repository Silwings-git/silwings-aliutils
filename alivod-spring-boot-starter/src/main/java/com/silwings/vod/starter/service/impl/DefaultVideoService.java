package com.silwings.vod.starter.service.impl;

import com.silwings.common.utils.JsonUtils;
import com.silwings.common.utils.RedisUtil;
import com.silwings.common.utils.SnowFlake;
import com.silwings.vod.starter.pojo.vod.dto.VideoMessageDto;
import com.silwings.vod.starter.properties.RedisProperties;
import com.silwings.vod.starter.utils.VideoTranscodeUtil;
import com.silwings.vod.starter.utils.VideoUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author CuiYiXiang
 * @Classname DefaultVideoService
 * @Description 视频服务接口的默认实现类
 * @Date 2020/7/30
 */
public class DefaultVideoService {

    private static final Logger logger = LoggerFactory.getLogger(VideoTranscodeUtil.class);


    @Autowired
    private RedisUtil vodRedisUtil;

    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private VideoUtils vodVideoUtils;

    /**
     * description: 根据文件大小获取hashKey
     * version: 1.0
     * date: 2020/7/2 21:09
     * author: 崔益翔
     *
     * @param size
     * @return R
     */
    public String getUpLoadHashKey(String size) {
        return size + System.currentTimeMillis() + UUID.randomUUID();
    }



    public VideoMessageDto getUpStatus(String hashKey) {
        VideoMessageDto videoMessageDto = null;
//        查询缓存中的该视频状态
        Object redisData = vodRedisUtil.get(redisProperties.getVideoSoleId() + hashKey);
        if (null == redisData) {
//            如果是空，直接设置状态码为失败，并返回，无需删除redis数据
            videoMessageDto = new VideoMessageDto();
            videoMessageDto.setUpStatus(VideoMessageDto.VIDEO_UPLOAD_FAIL);
            return videoMessageDto;
        }
        String status = (String) redisData;
//        判空
        if (StringUtils.isEmpty(status)) {
//            如果是空，直接设置状态码为失败，并返回，无需删除redis数据
            videoMessageDto = new VideoMessageDto();
            videoMessageDto.setUpStatus(VideoMessageDto.VIDEO_UPLOAD_FAIL);
            return videoMessageDto;
        }
        if (VideoMessageDto.VIDEO_UPLOAD_SUCCESS.equals(status)) {
//            已经上传成功，获取返回信息
            Object result = vodRedisUtil.get(redisProperties.getVideoDetailSaveId() + hashKey);
//            redis返回的实质是一个字符串，所以在不确定的情况下将其转换成json再转对象，直接强转会报转换异常
            String json = null;
            if (null != result) {
                json = JsonUtils.toJson(result);
            } else {
//            如果是空，直接设置状态码为失败，并返回，无需删除redis数据
                videoMessageDto = new VideoMessageDto();
                videoMessageDto.setUpStatus(VideoMessageDto.VIDEO_UPLOAD_FAIL);
                return videoMessageDto;
            }
            videoMessageDto = JsonUtils.toBean(json, VideoMessageDto.class);
            String videoId = videoMessageDto.getVideoId();
//            查询视频播放地址
            String url = null;
            try {
                url = vodVideoUtils.getUrl(videoId);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("获取视频信息失败");
            }
            videoMessageDto.setUrl(url);
//            将redis中的信息设置为自动过期。（10秒是为了配合前端的轮询器，有可能出现获取到成功信息，redis已经删除，定时器又发了请求）
            vodRedisUtil.set(redisProperties.getVideoSoleId() + hashKey, status, 10L);
            vodRedisUtil.set(redisProperties.getVideoDetailSaveId() + hashKey, videoMessageDto, 10L);
        }
        if (VideoMessageDto.VIDEO_UPLOAD_WAIT.equals(status)) {
//            正在上传
            videoMessageDto = new VideoMessageDto();
        }
        if (VideoMessageDto.VIDEO_UPLOAD_FAIL.equals(status)) {
//            上传失败
            videoMessageDto = new VideoMessageDto();
//            上传失败，删除redis中的信息
            vodRedisUtil.remove(redisProperties.getVideoSoleId() + hashKey);
        }
//        设置状态
        videoMessageDto.setUpStatus(status);
        return videoMessageDto;
    }


    /**
     * description: 生成系统随机名
     * version: 1.0
     * date: 2020/7/31 14:48
     * author: 崔益翔
     *
     * @param nameSuffix
     * @return VideoMessageDto
     */
    public VideoMessageDto creatNewFileName(String nameSuffix) {
        SnowFlake snowFlake = new SnowFlake();
//            生成文件名
        long randomNum = snowFlake.nextId();
//            自定义标题
        String title = randomNum + nameSuffix;
//            自定义文件名
        String fileName = title;
        return new VideoMessageDto(title, fileName);
    }


}
