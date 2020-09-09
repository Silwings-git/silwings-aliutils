package com.silwings.vod.starter.service;

import com.silwings.vod.starter.pojo.vod.dto.VideoMessageDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname VideoService
 * @Description TODO
 * @Date 2020/7/30
 */
public interface VideoService {
    /**
     * description: 获取上传码
     * version: 1.0
     * date: 2020/7/30 10:35
     * author: 崔益翔
     *
     * @param size
     * @return java.lang.String
     */
    String getUpLoadHashKey(String size);


    /**
     * description: 视频上传
     * version: 1.0
     * date: 2020/7/30 10:36
     * author: 崔益翔
     *
     * @param file
     * @return VideoMessageDto
     */
    VideoMessageDto videoUpload(MultipartFile file, String hashKey);

    /**
     * description: 获取视频上传状态
     * version: 1.0
     * date: 2020/7/31 10:16
     * author: 崔益翔
     * @param hashKey
     * @return VideoMessageDto
     */
    VideoMessageDto getUpStatus(String hashKey);
}
