package com.silwings.img.starter.service;

import com.silwings.img.starter.pojo.dto.ImgInputStreamDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CuiYiXiang
 * @Classname ImgCompressService
 * 压缩图片服务
 * @Description TODO
 * @Date 2020/8/2
 */
public interface ImgCompressService {

    ImgInputStreamDto imgCompress(MultipartFile upfile, String newFileName, Float compressLevelValue);
}
