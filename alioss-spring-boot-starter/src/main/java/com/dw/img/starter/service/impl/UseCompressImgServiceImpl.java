package com.dw.img.starter.service.impl;

import com.dw.img.starter.pojo.dto.ImgInputStreamDto;
import com.dw.img.starter.properties.CompressLevelMappingProperties;
import com.dw.img.starter.service.ImgCompressService;
import com.dw.img.starter.service.ImgService;
import com.dw.img.starter.service.ImgUpLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author CuiYiXiang
 * @Classname UseCompressImgServiceImpl
 * 压缩图片上传
 * @Description TODO
 * @Date 2020/8/2
 */
public class UseCompressImgServiceImpl extends DefaultImgServiceImpl implements ImgService {

    private static final Logger logger = LoggerFactory.getLogger(ImgCompressServiceImpl.class);


    @Autowired
    private ImgCompressService ossImgCompressService;

    @Autowired
    private ImgUpLoader ossImgUpLoader;

    @Autowired
    private CompressLevelMappingProperties compressLevelMappingProperties;

    @Override
    public String upFile(MultipartFile upfile, Integer compressLevel, String fileName) throws RuntimeException {
//        选择压缩比例
        if (null == compressLevel) {
            compressLevel = 0;
        }
        Float compressLevelValue = compressLevelMappingProperties.getCompressLevelValue(compressLevel);
        ImgInputStreamDto imgInputStreamDto = null;
        String url = null;
        try {
//          进行图片压缩
            imgInputStreamDto = ossImgCompressService.imgCompress(upfile, fileName, compressLevelValue);
//          图片上传
            url = ossImgUpLoader.upFile(imgInputStreamDto.getInputStream(), fileName);
//          删除缓存
        } catch (RuntimeException e) {
            e.printStackTrace();
            logger.error("图片上传失败");
            throw e;
        } finally {
            File file = imgInputStreamDto.getFile();
            if (null != file) {
                boolean delete = file.delete();
                if (delete) {
                    logger.info("缓存图片删除成功");
                } else {
                    logger.error("缓存图片删除失败");
                }
            }
        }
        return url;
    }
}
