package com.silwings.vod.starter.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.silwings.common.utils.JsonUtils;
import com.silwings.common.utils.RedisUtil;
import com.silwings.vod.starter.pojo.vod.dto.FileInputStreamDto;
import com.silwings.vod.starter.pojo.vod.dto.VideoMessageDto;
import com.silwings.vod.starter.properties.RedisProperties;
import com.silwings.vod.starter.properties.VideoProperties;
import com.silwings.vod.starter.service.VideoUpLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.InputStream;

/**
 * @author CuiYiXiang
 * @Classname DefaultVideoUpLoader
 * @Description 视频上传器, 只负责上传视频与异步缓存赋值
 * @Date 2020/7/31
 */
public class DefaultVideoUpLoader implements VideoUpLoader {

    private static final Logger logger = LoggerFactory.getLogger(DefaultVideoUpLoader.class);


    @Autowired
    private RedisUtil vodRedisUtil;

    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private VideoProperties videoProperties;

    /**
     * description: 异步上传视频
     * version: 1.0
     * date: 2020/9/11 8:16
     * author: 崔益翔
     *
     * @param fileInputStreamDto 文件与流信息
     * @param videoMessage       视频信息
     * @param hashKey            资源标识符
     * @return void
     */
    @Override
    public void asyncExecuteVideoUpload(FileInputStreamDto fileInputStreamDto, VideoMessageDto videoMessage, String hashKey) {
        try {
//          执行文件上传
            VideoMessageDto videoMessageDto = this.executeVideoUpload(fileInputStreamDto.getInputStream(), videoMessage);
//          设置上传状态信息
            this.setVideoMessageToRedis(videoMessageDto, hashKey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//          删除缓存
            File file = fileInputStreamDto.getFile();
            if (null != file) {
                boolean delete = file.delete();
                if (delete) {
                    logger.info("新文件缓存清理成功");
                } else {
                    logger.error("新文件缓存清理失败");
                }
            } else {
                logger.info("没有缓存文件");
            }
        }
    }


    /**
     * description: 执行视频文件上传
     * version: 1.0
     * date: 2020/7/31 13:07
     * author: 崔益翔
     *
     * @param inputStream  文件输入流
     * @param videoMessage 视频文件信息
     * @return java.lang.String 阿里云唯一视频id,如果上传失败返回空
     */
    @Override
    public VideoMessageDto executeVideoUpload(InputStream inputStream, VideoMessageDto videoMessage) {
        logger.info("线程：" + Thread.currentThread().getName() + "开始上传文件...");
        long start = System.currentTimeMillis();
        //            创建请求对象
        UploadStreamRequest request = new UploadStreamRequest(
                videoProperties.getAccessKeyId(),
                videoProperties.getAccessKeySecret(),
                videoMessage.getTitle(),
//                    fileName必须带后缀
                videoMessage.getFileNameId(),
                inputStream
        );
//            上传进度打印
        request.setPrintProgress(videoProperties.isPrintProgress());
//            设置分类id（文件夹后的数字），暂时没有
//            request.setCateId(0L);
//            创建文件上传器
        UploadVideoImpl uploadVider = new UploadVideoImpl();
//            执行文件上传
        UploadStreamResponse response = uploadVider.uploadStream(request);
//            获取videoId
        String videoId = response.getVideoId();
//            判断文件上传是否成功
        if (!response.isSuccess()) {
//                如果设置回调url无效，不影响视频上传，可以返回videoId，同时返回错误码
//                其他情况上传失败时，videoId为空。此时需要根据返回的错误码分析原因
            String errorMessage = "阿里云上传错误：" + "code: " + response.getCode() + ",message: " + response.getMessage();
            logger.error(errorMessage);
        }
        long end = System.currentTimeMillis();
        logger.info("线程：" + Thread.currentThread().getName() + "视频上传成功.videoId: " + videoId);
        logger.info("视频上传耗时: " + (end - start) + " 毫秒");
        return videoMessage.setVideoId(videoId);
    }

    /**
     * description: 设置视频上传状态信息到redis
     * 为了减少redis负担,只存储视频的id和成功与否信息
     * 视频的播放信息在获取状态时查询
     * version: 1.0
     * date: 2020/7/31 13:09
     * author: 崔益翔
     *
     * @param videoMessage
     * @return void
     */
    private void setVideoMessageToRedis(VideoMessageDto videoMessage, String hashKey) {
        if (StringUtils.isEmpty(videoMessage.getVideoId())) {
//            说明文件上传失败
            vodRedisUtil.set(redisProperties.getVideoSoleId() + hashKey, VideoMessageDto.VIDEO_UPLOAD_FAIL);
            logger.error("线程：" + Thread.currentThread().getName() + "视频上传失败");
            return;
        }
        String json = JsonUtils.toJson(videoMessage);
//            将上传文件的信息设置到缓存(上传成功时先设置成功信息,防止因为网络原因导致获取到成功但获取不到视频信息)
        vodRedisUtil.set(redisProperties.getVideoDetailSaveId() + hashKey, json);
//            将上传状态设置为成功
        vodRedisUtil.set(redisProperties.getVideoSoleId() + hashKey, VideoMessageDto.VIDEO_UPLOAD_SUCCESS);
        logger.info("线程：" + Thread.currentThread().getName() + " 文件上传成功；");
    }
}
