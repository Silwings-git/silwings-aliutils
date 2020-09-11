package com.silwings.img.starter.controller;

import com.silwings.common.entity.R;
import com.silwings.img.starter.config.ImgUrlConfig;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname OssImgController
 * @Description 图片上传控制器.类级映射路径通过配置文件配置
 * @Date 2020/8/2
 */
@RestController
@RequestMapping(ImgUrlConfig.URL)
public interface OssImgController {

    /**
     * description: 图片上传统一web接口
     * version: 1.0
     * date: 2020/9/11 7:48
     * author: 崔益翔
     * @param upFile 需要上传的图片
     * @param compressLevel 图片压缩等级
     * @return com.silwings.common.entity.R 文件存储路径
     */
    @RequestMapping(value = "/uploadImg", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    R<String> uploadImg(@RequestParam MultipartFile upFile,
                @RequestParam(value = "compressLevel", defaultValue = "") Integer compressLevel);
}
