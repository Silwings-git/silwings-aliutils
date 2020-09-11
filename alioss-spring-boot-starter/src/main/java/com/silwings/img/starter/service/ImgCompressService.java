package com.silwings.img.starter.service;

import com.silwings.img.starter.pojo.dto.ImgInputStreamDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname ImgCompressService
 * @Description  压缩图片服务
 * @Date 2020/8/2
 */
public interface ImgCompressService {

    /**
     * description: 根据压缩等级压缩/放大图片
     * version: 1.0
     * date: 2020/9/11 7:54
     * author: 崔益翔
     * @param upFile 需要操作的图片
     * @param newFileName 新文件名称
     * @param compressLevelValue 压缩等级
     * @return com.silwings.img.starter.pojo.dto.ImgInputStreamDto 压缩后的文件流
     */
    ImgInputStreamDto imgCompress(MultipartFile upFile, String newFileName, Float compressLevelValue);
}
