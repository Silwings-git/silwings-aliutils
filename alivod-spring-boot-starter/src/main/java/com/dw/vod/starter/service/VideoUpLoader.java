package com.dw.vod.starter.service;

import com.dw.vod.starter.pojo.vod.dto.FileInputStreamDto;
import com.dw.vod.starter.pojo.vod.dto.VideoMessageDto;
import org.springframework.scheduling.annotation.Async;

import java.io.InputStream;

/**
 * @author CuiYiXiang
 * @Classname VideoUpLoader
 * @Description TODO
 * @Date 2020/7/31
 */
public interface VideoUpLoader {

    @Async("vodTaskExecutor")
    void asyncExecuteVideoUpload(FileInputStreamDto fileInputStreamDto, VideoMessageDto videoMessage, String hashKey);

    VideoMessageDto executeVideoUpload(InputStream inputStream, VideoMessageDto videoMessage);
}
