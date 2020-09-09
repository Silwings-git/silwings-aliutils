package com.silwings.vod.starter.service.impl;

import com.silwings.vod.starter.pojo.vod.dto.FileInputStreamDto;
import com.silwings.vod.starter.pojo.vod.dto.VideoMessageDto;
import com.silwings.vod.starter.properties.VideoProperties;
import com.silwings.vod.starter.service.VideoService;
import com.silwings.vod.starter.service.VideoUpLoader;
import com.silwings.vod.starter.utils.VideoTranscodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author CuiYiXiang
 * @Classname SyncVideoServiceImpl
 * 同步上传服务.
 * @Description TODO
 * @Date 2020/7/31
 */
public class SyncVideoServiceImpl extends DefaultVideoService implements VideoService {

    @Autowired
    private VideoTranscodeUtil vodVideoTranscodeUtil;

    @Autowired
    private VideoProperties videoProperties;

    private static final Logger logger = LoggerFactory.getLogger(DefaultVideoService.class);

    public SyncVideoServiceImpl() {
        logger.info("同步上传服务初始化中...");
    }

    @Autowired
    private VideoUpLoader vodVideoUpLoader;

    /**
     * description: 同步文件上传服务
     * version: 1.0
     * date: 2020/8/1 19:40
     * author: 崔益翔
     * @param file 文件对象
     * @param isNull 直接传null]即可,代码中不会使用到
     * @return VideoMessageDto
     */
    @Override
    public VideoMessageDto videoUpload(MultipartFile file, String isNull) {
        String fileName = file.getOriginalFilename();
        String nameSuffix = fileName.substring(fileName.lastIndexOf("."));
        if (StringUtils.isEmpty(nameSuffix)) {
//            不执行上传
            return null;
        }
//        获取文件流(如果不是mp4后缀名将进行转码)
        FileInputStreamDto fileInputStreamDto = vodVideoTranscodeUtil.getInputStream(file, nameSuffix);
//        视频信息
        VideoMessageDto videoMessage = this.creatNewFileName(videoProperties.getVideoSuffix());
//        执行视频文件上传
        VideoMessageDto videoMessageDto = vodVideoUpLoader.executeVideoUpload(fileInputStreamDto.getInputStream(), videoMessage);
        logger.info("视频上传结束");
        return videoMessageDto;
    }
}
