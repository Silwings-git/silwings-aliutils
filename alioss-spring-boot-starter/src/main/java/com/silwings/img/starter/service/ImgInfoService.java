package com.silwings.img.starter.service;

import java.util.List;

/**
 * @author CuiYiXiang
 * @Classname ImgInfoService
 * @Description TODO
 * @Date 2020/8/2
 */
public interface ImgInfoService {
    /**
     * description: 批量删除图片
     * version: 1.0
     * date: 2020/8/2 22:15
     * author: 崔益翔
     * @param keys 需要删除的图片的key集合.key为图片路径.包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
     * @return void
     */
    void deleteImg(List<String> keys);
}
