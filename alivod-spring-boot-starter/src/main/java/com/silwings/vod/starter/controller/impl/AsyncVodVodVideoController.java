package com.silwings.vod.starter.controller.impl;

import com.silwings.vod.starter.controller.VodVideoController;
import com.silwings.vod.starter.pojo.vod.dto.VideoMessageDto;
import com.silwings.vod.starter.pojo.vod.vo.VideoMessageVo;
import com.silwings.vod.starter.service.VideoService;
import com.silwings.common.entity.R;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname DemoController
 * @Description TODO
 * @Date 2020/7/31
 */
public class AsyncVodVodVideoController implements VodVideoController {

    private static final Logger logger = LoggerFactory.getLogger(AsyncVodVodVideoController.class);


    @Autowired
    private VideoService vodVideoService;

    public AsyncVodVodVideoController() {
        logger.info("异步上传控制器初始化中...");
    }

    /**
     * description: 根据文件大小获取hashKey
     * version: 1.0
     * date: 2020/7/2 21:09
     * author: 崔益翔
     *
     * @param size
     * @return com.dw.cloud.R
     */
    @Override
    public R getUpLoadHashKey(String size) {
        String upLoadHashKey = vodVideoService.getUpLoadHashKey(size);
        return R.success(upLoadHashKey);
    }


    /**
     * description: 视频文件上传
     * 注意：视频不符合时长限制时，不能直接通过R.fail()返回。因为目前视频上传的逻辑中前端并不会理会该接口的
     * 返回值，而是去调用getUpStatus接口获取。所以需要将失败消息存储到redis，由getUpStatus接口返回给前端
     * version: 1.0
     * date: 2020/7/1 23:43
     * author: 崔益翔
     *
     * @param file 文件
     * @return com.dw.cloud.R 文件videoId和url
     */
    @Override
    public R videoUpload(String hashKey, MultipartFile file) {
        try {
            if (null == file || StringUtils.isEmpty(hashKey)) {
                return R.fail("上传失败");
            }

            vodVideoService.videoUpload(file, hashKey);
            return R.success(hashKey);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }


    /**
     * description: 根据上传状态获取码获取文件上传状态
     * version: 1.0
     * date: 2020/7/2 19:12
     * author: 崔益翔
     *
     * @param hashKey 获取文件上传状态的请求码
     * @return com.dw.cloud.R
     */
    @Override
    public R getUpStatus(String hashKey) {
        VideoMessageDto videoMessageDto = vodVideoService.getUpStatus(hashKey);
        return R.success(VideoMessageVo.getInstance(videoMessageDto));
    }

}
