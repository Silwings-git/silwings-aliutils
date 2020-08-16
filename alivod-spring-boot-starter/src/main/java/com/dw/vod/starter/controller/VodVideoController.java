package com.dw.vod.starter.controller;

import com.dw.common.entity.R;
import com.dw.vod.starter.config.VideoConfig;
import com.dw.vod.starter.config.VideoUrlConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname VodVideoController
 * @Description TODO
 * @Date 2020/8/1
 */
@RestController
@RequestMapping(VideoUrlConfig.URL)
public interface VodVideoController {

    /**
     * description: 根据文件大小获取hashKey
     * version: 1.0
     * date: 2020/8/1 20:16
     * author: 崔益翔
     * @param size
     * @return com.dw.common.entity.R
     */
    @RequestMapping(value = "/getUpLoadHashKey", method = {RequestMethod.GET, RequestMethod.POST})
    R getUpLoadHashKey(
            @RequestParam("size") String size);

    /**
     * description: 文件上传接口
     * version: 1.0
     * date: 2020/8/1 20:17
     * author: 崔益翔
     * @param hashKey
     * @param file
     * @return com.dw.common.entity.R
     */
    @RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
    R videoUpload(
            @RequestParam(value = "hashKey", defaultValue = "") String hashKey,
            @RequestParam(value = "file") MultipartFile file);

    /**
     * description: 获取文件上传状态
     * version: 1.0
     * date: 2020/8/1 20:17
     * author: 崔益翔
     * @param hashKey
     * @return com.dw.common.entity.R
     */
    @RequestMapping(value = "/getUpStatus", method = {RequestMethod.GET, RequestMethod.POST})
    R getUpStatus(
            @RequestParam("hashKey") String hashKey);
}
