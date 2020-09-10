package com.silwings.img.starter.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname ImgService
 * @Description TODO
 * @Date 2020/8/2
 */
public interface ImgService {

    String createNewFileName(MultipartFile upFile);

    /**
     * @param upFile 文件上传
     * @param compressLevel 压缩等级
     * @return
     * @throws RuntimeException
     */
     String upFile(MultipartFile upFile, Integer compressLevel,String fileName) throws RuntimeException;

}
