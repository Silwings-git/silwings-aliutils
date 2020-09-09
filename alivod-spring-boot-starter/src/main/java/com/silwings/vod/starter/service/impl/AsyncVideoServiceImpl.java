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
 * @Classname AsyncVideoServiceImpl
 * 异步上传服务.
 * @Description TODO
 * @Date 2020/7/29
 */
public class AsyncVideoServiceImpl extends DefaultVideoService implements VideoService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncVideoServiceImpl.class);

    @Autowired
    private VideoTranscodeUtil vodVideoTranscodeUtil;

    @Autowired
    private VideoProperties videoProperties;


    public AsyncVideoServiceImpl() {
        logger.info("异步上传服务初始化中...");
    }

    @Autowired
    private VideoUpLoader vodVideoUpLoader;

    /**
     * description:
     * 上传视频,并将视频上传状态存储到redis数据库
     * 注意:
     * 统一使用mp4格式
     * version: 1.0
     * date: 2020/7/30 10:40
     * author: 崔益翔
     *
     * @param file
     * @param hashKey
     * @return VideoMessageDto
     */
    @Override
    public VideoMessageDto videoUpload(MultipartFile file, String hashKey) {
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
        logger.info("线程：" + Thread.currentThread().getName() + "准备调用文件上传");
//        执行视频文件上传
        vodVideoUpLoader.asyncExecuteVideoUpload(fileInputStreamDto, videoMessage, hashKey);

        logger.info("方法已返回");
        return videoMessage;
    }


}
