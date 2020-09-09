package com.silwings.img.starter.controller;

import com.silwings.common.entity.R;
import com.silwings.img.starter.config.ImgUrlConfig;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname OssImgController
 * @Description TODO
 * @Date 2020/8/2
 */
@RestController
@RequestMapping(ImgUrlConfig.URL)
public interface OssImgController {

    @RequestMapping(value = "/uploadImg", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    R uploadImg(@RequestParam MultipartFile upfile,
                @RequestParam(value = "compressLevel", defaultValue = "") Integer compressLevel);
}
