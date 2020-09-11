package com.silwings.img.starter.service;

import java.io.InputStream;
import java.util.List;

/**
 * @author CuiYiXiang
 * @Classname ImgUpLoader
 * @Description 视频上传器
 * @Date 2020/8/2
 */
public interface ImgUpLoader {
    /**
     * description: 执行文件上传
     * version: 1.0
     * date: 2020/9/11 8:03
     * author: 崔益翔
     * @param inputStream 文件输入流
     * @param fileName 文件名
     * @return java.lang.String
     */
    String upFile(InputStream inputStream, String fileName) throws RuntimeException;

    /**
     * description: 删除文件
     * version: 1.0
     * date: 2020/9/11 8:03
     * author: 崔益翔
     * @param keys
     * @return boolean
     */
    boolean delete(List<String> keys);
}
