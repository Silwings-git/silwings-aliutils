package com.silwings.img.starter.service.impl;

import com.silwings.img.starter.service.ImgService;
import com.silwings.img.starter.service.ImgUpLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * @author CuiYiXiang
 * @Classname DefaultImgServiceImpl
 * @Description TODO
 * @Date 2020/8/2
 */
public class DefaultImgServiceImpl implements ImgService {

    @Autowired
    private ImgUpLoader ossImgUpLoader;

    /**
     * description: 创建新的系统文件名
     * version: 1.0
     * date: 2020/8/2 17:05
     * author: 崔益翔
     *
     * @param upfile
     * @return java.lang.String
     */
    @Override
    public String createNewFileName(MultipartFile upfile) {
        String fileName = upfile.getOriginalFilename();
        String substring = fileName.substring(fileName.lastIndexOf("/") + 1);
        String newName = substring.substring(substring.lastIndexOf("\\") + 1);
        //新文件名
        return createNonceStr() + "_" + newName;
    }

    /**
     * 随机数生成
     *
     * @return
     */
    public String createNonceStr() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    @Override
    public String upFile(MultipartFile upfile, Integer compressLevel,String fileName) throws RuntimeException {
        String url = null;
        try {
            InputStream input = upfile.getInputStream();
            url = ossImgUpLoader.upFile(input, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return url;
    }
}
