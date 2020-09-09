package com.silwings.img.starter.controller.impl;

import com.silwings.common.entity.R;
import com.silwings.img.starter.config.ImgConfig;
import com.silwings.img.starter.controller.OssImgController;
import com.silwings.img.starter.service.ImgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname OssImgControllerImpl
 * @Description TODO
 * @Date 2020/8/2
 */
public class OssImgControllerImpl implements OssImgController {

    private static final Logger logger = LoggerFactory.getLogger(ImgConfig.class);

    @Autowired
    private ImgService ossImgService;

    @Override
    public R uploadImg(MultipartFile upfile, Integer compressLevel){
        logger.info("开始图片上传...");
        String strBackUrl = null;
        try {
            String fileName = ossImgService.createNewFileName(upfile);
            strBackUrl = ossImgService.upFile(upfile, compressLevel,fileName);
        } catch (RuntimeException e) {
            e.printStackTrace();
            logger.error("图片上传失败");
            return R.fail("上传失败,请稍后重试");
        }
        logger.info("图片上传完成");
        return R.success(strBackUrl);
    }
}
