package com.silwings.img.starter.service.impl;

import com.silwings.img.starter.service.ImgInfoService;
import com.silwings.img.starter.service.ImgUpLoader;
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

    /**
     * description: 批量删除图片
     * version: 1.0
     * date: 2020/9/10 19:44
     * author: 崔益翔
     * @param imgs 需要删除的图片的key集合.key为图片路径.包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
     * @return void
     */
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
