package com.silwings.img.starter.service.impl;

import com.silwings.img.starter.service.ImgService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname NoCompressImgServiceImpl
 * 不压缩
 * @Description TODO
 * @Date 2020/8/2
 */
public class NoCompressImgServiceImpl extends DefaultImgServiceImpl implements ImgService {

    @Override
    public String upFile(MultipartFile upfile, Integer compressLevel, String fileName) throws RuntimeException {
        return super.upFile(upfile, compressLevel, fileName);
    }
}
