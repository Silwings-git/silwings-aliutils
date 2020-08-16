package com.dw.vod.starter.pojo.vod.vo;

import com.dw.vod.starter.pojo.vod.dto.VideoMessageDto;
import org.springframework.beans.BeanUtils;

/**
 * @author CuiYiXiang
 * @Classname VideoMessageVo
 * @Description TODO
 * @Date 2020/7/30
 */
public class VideoMessageVo extends VideoMessageDto {

    /**
     * description: 通过 VideoMessageDto 对象构建一个VideoMessageVo对象。如果VideoMessageDto为空，
     * 将返回一个全部属性为空的VideoMessageVo对象
     * version: 1.0
     * date: 2020/7/30 7:47
     * author: 崔益翔
     *
     * @param videoMessageDto
     * @return VideoMessageVo
     */
    public static VideoMessageVo getInstance(VideoMessageDto videoMessageDto) {
        VideoMessageVo videoMessageVo = new VideoMessageVo();
        if (null == videoMessageDto) {
            return videoMessageVo;
        }
        BeanUtils.copyProperties(videoMessageDto, videoMessageVo);
        return videoMessageVo;
    }

    /**
     * description: 获取一个全部属性为空的 VideoMessageVo对象，调用此方法等同于new
     * version: 1.0
     * date: 2020/7/30 7:48
     * author: 崔益翔
     * @param
     * @return VideoMessageVo
     */
    public static VideoMessageVo getInstance() {
        return new VideoMessageVo();
    }
}
