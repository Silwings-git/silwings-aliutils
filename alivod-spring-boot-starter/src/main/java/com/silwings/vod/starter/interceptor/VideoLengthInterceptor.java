package com.silwings.vod.starter.interceptor;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author CuiYiXiang
 * @Classname VideoLengthInterceptor
 * @Description TODO
 * @Date 2020/8/4
 */
public class VideoLengthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MultipartHttpServletRequest muRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = muRequest.getFileMap();



//        // 转换请求对象得到文件对象
//        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) request;
//        Map<String, MultipartFile> files = Murequest.getFileMap();
//        String filePath;
//        File tagetFile;
//        // 获得文档保存目录
//        File dir = new File(savePath);
//        if (!dir.exists()) {
//            System.out.println("文件目录创建中。。。");
//            dir.mkdirs();
//        }
//        // 保存文档
//        for (MultipartFile file : files.values()) {
//            // 创建文件对象
//            tagetFile = new File(filePath);
//            // 目标文件创建
//            try {
//                tagetFile.createNewFile();
//                file.transferTo(tagetFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//                json.setErrMsg("保存文件异常，请重新保存文件!");
//            }
//        }
        return false;
    }
}
