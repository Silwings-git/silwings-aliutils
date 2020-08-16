package com.dw.img.starter.service.impl;

import com.dw.img.starter.pojo.dto.ImgInputStreamDto;
import com.dw.img.starter.service.ImgCompressService;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author CuiYiXiang
 * @Classname ImgCompressServiceImpl
 * 图片压缩服务
 * @Description TODO
 * @Date 2020/8/2
 */
public class ImgCompressServiceImpl implements ImgCompressService {
    private static final Logger logger = LoggerFactory.getLogger(ImgCompressServiceImpl.class);

    @Override
    public ImgInputStreamDto imgCompress(MultipartFile upfile, String newFileName, Float compressLevelValue) {
        logger.info("压缩比例" + compressLevelValue + ",开始压缩图片...");
        long size = upfile.getSize();
        File file = null;
        InputStream input = null;
        FileInputStream inputStream = null;
        try {
            input = upfile.getInputStream();
//        图片压缩
            file = new File(newFileName);
            Thumbnails.of(input)
//                    压缩比例
                    .scale(compressLevelValue)
//                    输出缓存文件质量
                    .outputQuality(0.8f)
                    .toFile(file);
            inputStream = new FileInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long newSize = file.length();
        logger.info("原图片大小 = " + size);
        logger.info("压缩后图片大小 = " + newSize);
        return new ImgInputStreamDto().setFile(file).setInputStream(inputStream);
    }

}
