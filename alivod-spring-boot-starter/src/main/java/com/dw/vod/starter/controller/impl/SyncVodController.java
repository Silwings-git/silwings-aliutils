package com.dw.vod.starter.controller.impl;

import com.dw.common.entity.R;
import com.dw.vod.starter.controller.VodVideoController;
import com.dw.vod.starter.pojo.vod.dto.VideoMessageDto;
import com.dw.vod.starter.pojo.vod.vo.VideoMessageVo;
import com.dw.vod.starter.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname SyncVodController
 * @Description TODO
 * @Date 2020/8/1
 */
public class SyncVodController implements VodVideoController {

    private static final Logger logger = LoggerFactory.getLogger(SyncVodController.class);


    @Autowired
    private VideoService vodVideoService;

    public SyncVodController() {
        logger.info("同步上传控制器初始化中...");
    }

    /**
     * description: 同步视频文件上传
     * version: 1.0
     * date: 2020/8/1 19:35
     * author: 崔益翔
     *
     * @param file
     * @return com.dw.common.entity.R
     */
    @Override
    public R videoUpload(String hashKey, MultipartFile file) {
        try {
            if (null == file) {
                return R.fail("文件为空");
            }
            VideoMessageDto videoMessageDto = vodVideoService.videoUpload(file, null);
            return R.success(VideoMessageVo.getInstance(videoMessageDto));
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    /**
     * description: 同步上传无需获取hashKey
     * version: 1.0
     * date: 2020/8/1 20:14
     * author: 崔益翔
     * @param size
     * @return com.dw.common.entity.R
     */
    @Override
    public R getUpLoadHashKey(String size) {
        return R.fail("路径错误");
    }

    @Override
    public R getUpStatus(String hashKey) {
        return R.fail("路径错误");
    }
}
