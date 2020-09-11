package com.silwings.img.starter.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname ImgService
 * @Description TODO
 * @Date 2020/8/2
 */
public interface ImgService {

    /**
     * description: 创建新的文件名称
     * version: 1.0
     * date: 2020/9/11 7:56
     * author: 崔益翔
     * @param upFile 需要命名的文件
     * @return java.lang.String
     */
    String createNewFileName(MultipartFile upFile);

    /**
     * description: 图片上传
     * version: 1.0
     * date: 2020/9/11 7:57
     * author: 崔益翔
     * @param upFile 上传的图片文件
     * @param compressLevel 图片压缩等级
     * @param fileName 文件名称
     * @return java.lang.String 文件路径
     */
     String upFile(MultipartFile upFile, Integer compressLevel,String fileName) throws RuntimeException;

}
