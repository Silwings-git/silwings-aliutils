package com.dw.img.starter.service;

import java.io.InputStream;
import java.util.List;

/**
 * @author CuiYiXiang
 * @Classname ImgUpLoader
 * @Description TODO
 * @Date 2020/8/2
 */
public interface ImgUpLoader {
    String upFile(InputStream inputStream, String fileName) throws RuntimeException;

    boolean delete(List<String> imgs);
}
