package com.dw.img.starter.service.impl;

import com.dw.img.starter.service.ImgInfoService;
import com.dw.img.starter.service.ImgUpLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author CuiYiXiang
 * @Classname ImgInfoServiceImpl
 * @Description TODO
 * @Date 2020/8/2
 */
public class ImgInfoServiceImpl implements ImgInfoService {

    private static final Logger logger = LoggerFactory.getLogger(ImgInfoServiceImpl.class);

    @Autowired
    private ImgUpLoader ossImgUpLoader;

    @Override
    public void deleteImg(List<String> imgs) {
        boolean delete = ossImgUpLoader.delete(imgs);
        if (delete) {
            logger.info("图片删除成功");
        } else {
            logger.error("图片删除失败");
        }
    }
}
