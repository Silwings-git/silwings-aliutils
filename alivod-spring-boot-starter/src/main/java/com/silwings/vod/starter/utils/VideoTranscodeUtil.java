package com.silwings.vod.starter.utils;

import com.silwings.common.utils.MultipartFileToFile;
import com.silwings.common.utils.SnowFlake;
import com.silwings.vod.starter.pojo.vod.dto.FileInputStreamDto;
import com.silwings.vod.starter.properties.VideoProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author CuiYiXiang
 * @Classname VideoTranscodeUtil
 * 视频格式转码
 * @Description TODO
 * @Date 2020/7/31
 */
public class VideoTranscodeUtil {

    private static final Logger logger = LoggerFactory.getLogger(VideoTranscodeUtil.class);


    @Autowired
    private VideoProperties videoProperties;

    public FileInputStreamDto transcoding(MultipartFile file, String suffix) throws Exception {
        logger.info("执行视频转码...");
        File source = null;
        File target = null;
        InputStream inputStream = null;
        try {
            source = MultipartFileToFile.multipartFileToFile(file);
            long temporary = new SnowFlake(1, 1).nextId();
            target = new File("tmp" + temporary + ".mp4");
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("aac");
            VideoAttributes video = new VideoAttributes();
            video.setCodec("h264");
            video.setBitRate(new Integer(2325 * 1024));
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat(suffix);
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);
            inputStream = new FileInputStream(target);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boolean deleteSource = source.delete();
            if (deleteSource) {
                logger.info("源文件缓存清理成功");
            } else {
                logger.error("源文件缓存清理失败");
            }
        }
        FileInputStreamDto fileInputStreamDto = new FileInputStreamDto().setFile(target).setInputStream(inputStream);
        return fileInputStreamDto;
    }

    /**
     * description: 获取视频文件的input流,如果不是mp4文件将先进行转码
     * version: 1.0
     * date: 2020/7/31 10:53
     * author: 崔益翔
     *
     * @param file
     * @param nameSuffix
     * @return java.io.InputStream
     */
    public FileInputStreamDto getInputStream(MultipartFile file, String nameSuffix) {
        FileInputStreamDto fileInputStreamDto = null;
        if (!videoProperties.getVideoSuffix().equals(nameSuffix)) {
//            统一使用.mp4后缀名
            try {
                fileInputStreamDto = this.transcoding(file, videoProperties.getUnifyVideoSuffix());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                fileInputStreamDto = new FileInputStreamDto().setInputStream(file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileInputStreamDto;
    }

}
