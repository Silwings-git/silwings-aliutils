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

    /**
     * description: 不压缩直接上传
     * version: 1.0
     * date: 2020/9/11 8:07
     * author: 崔益翔
     * @param upFile 需要上传的图片文件
     * @param compressLevel 压缩等级(可以为空,非空也不会起作用)
     * @param fileName 文件名
     * @return java.lang.String 图片路径
     */
    @Override
    public String upFile(MultipartFile upFile, Integer compressLevel, String fileName) throws RuntimeException {
        return super.upFile(upFile, compressLevel, fileName);
    }
}
