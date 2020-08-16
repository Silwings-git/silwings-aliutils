package com.dw.vod.starter.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import com.dw.vod.starter.pojo.vod.dto.VideoInfo;
import com.dw.vod.starter.properties.VideoProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author CuiYiXiang
 * @Classname VideoUtils
 * @Description TODO 视频操作工具类
 * @Date 2020/7/29
 */
public class VideoUtils {

    private static final Logger logger = LoggerFactory.getLogger(VideoUtils.class);

    private VideoProperties videoProperties;

    public VideoUtils(VideoProperties videoProperties) {
        this.videoProperties = videoProperties;
    }

    /**
     * description: 初始化客户端
     * version: 1.0
     * date: 2020/7/29 23:23
     * author: 崔益翔
     *
     * @param accessKeyId     秘钥
     * @param accessKeySecret 秘钥
     * @return com.aliyuncs.DefaultAcsClient
     */
    public DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        //点播服务所在的Region，国内请填cn-shanghai，不要填写别的区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }

    /**
     * description: 获取视频信息
     * version: 1.0
     * date: 2020/7/29 23:24
     * author: 崔益翔
     *
     * @param videoId 视频id
     * @return com.aliyuncs.vod.model.v20170321.GetVideoInfoResponse
     */
    public GetVideoInfoResponse getVideoInfo(String videoId) throws Exception {
        DefaultAcsClient client = this.initVodClient(videoProperties.getAccessKeyId(), videoProperties.getAccessKeySecret());
        GetVideoInfoRequest request = new GetVideoInfoRequest();
        request.setVideoId(videoId);
        return client.getAcsResponse(request);
    }

    /**
     * description: 获取视频播放信息
     * version: 1.0
     * date: 2020/7/29 23:24
     * author: 崔益翔
     *
     * @param videoId 视频Id
     * @return com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse
     */
    public GetPlayInfoResponse getPlayInfo(String videoId) throws Exception {
        DefaultAcsClient client = this.initVodClient(videoProperties.getAccessKeyId(), videoProperties.getAccessKeySecret());
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(videoId);
        return client.getAcsResponse(request);
    }


    /**
     * description: 获取视频播放地址
     * version: 1.0
     * date: 2020/7/29 23:24
     * author: 崔益翔
     *
     * @param videoId 视频id
     * @return java.lang.String 播放地址
     */
    public String getUrl(String videoId) {
        try {
            GetPlayInfoResponse response = getPlayInfo(videoId);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            return playInfoList.get(0).getPlayURL();
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
            logger.error("获取视频封面信息失败");
            return null;
        }
    }


    /**
     * description: 获取播放凭证
     * version: 1.0
     * date: 2020/7/29 23:25
     * author: 崔益翔
     *
     * @param videoId 视频id
     * @return java.lang.String 播放凭证
     */
    public String getVideoPlayAuth(String videoId) throws Exception {
        DefaultAcsClient client = this.initVodClient(videoProperties.getAccessKeyId(), videoProperties.getAccessKeySecret());
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);
        String playAuth = response.getPlayAuth();
        return playAuth;
    }

    /**
     * description: 删除视频
     * version: 1.0
     * date: 2020/7/29 23:25
     * author: 崔益翔
     *
     * @param videoId 视频Id
     * @return com.aliyuncs.vod.model.v20170321.DeleteVideoResponse
     */
    public DeleteVideoResponse deleteVideo(String videoId) throws Exception {
        DefaultAcsClient client = this.initVodClient(videoProperties.getAccessKeyId(), videoProperties.getAccessKeySecret());
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videoId);
        return client.getAcsResponse(request);
    }

    /**
     * description: 删除视频
     * version: 1.0
     * date: 2020/7/29 23:25
     * author: 崔益翔
     *
     * @param videoIds 视频Id集合
     * @return com.aliyuncs.vod.model.v20170321.DeleteVideoResponse
     */
    public DeleteVideoResponse deleteVideo(List<String> videoIds) throws Exception {
        DefaultAcsClient client = this.initVodClient(videoProperties.getAccessKeyId(), videoProperties.getAccessKeySecret());
        DeleteVideoRequest request = new DeleteVideoRequest();
        String param = createRequestParam(videoIds);
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(param);
        return client.getAcsResponse(request);
    }

    private String createRequestParam(List<String> videoIds) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < videoIds.size(); i++) {
            buffer.append(videoIds.get(i));
            if (i != videoIds.size() - 1) {
                buffer.append(",");
            }
        }
        return buffer.toString();
    }

    /**
     * description: 刷新视频上传凭证
     * version: 1.0
     * date: 2020/7/29 23:26
     * author: 崔益翔
     *
     * @param videoId 视频id
     * @return com.aliyuncs.vod.model.v20170321.RefreshUploadVideoResponse 刷新视频上传凭证响应数据
     */
    public RefreshUploadVideoResponse refreshUploadVideo(String videoId) throws Exception {
        DefaultAcsClient client = this.initVodClient(videoProperties.getAccessKeyId(), videoProperties.getAccessKeySecret());
        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
        request.setVideoId(videoId);
        return client.getAcsResponse(request);
    }


    /**
     * description: 获取视频上传地址和凭证
     * version: 1.0
     * date: 2020/7/2 23:22
     * author: 崔益翔
     *
     * @param client   发送请求客户端
     * @param title
     * @param fileName
     * @return com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse 获取视频上传地址和凭证响应数据
     */
    public static CreateUploadVideoResponse createUploadVideo(DefaultAcsClient client, String title, String fileName) throws Exception {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle(title);
        request.setFileName(fileName);
        //UserData，用户自定义设置参数，用户需要单独回调URL及数据透传时设置(非必须)
        //JSONObject userData = new JSONObject();
        //UserData回调部分设置
        //JSONObject messageCallback = new JSONObject();
        //messageCallback.put("CallbackURL", "http://xxxxx");
        //messageCallback.put("CallbackType", "http");
        //userData.put("MessageCallback", messageCallback.toJSONString());
        //UserData透传数据部分设置
        //JSONObject extend = new JSONObject();
        //extend.put("MyId", "user-defined-id");
        //userData.put("Extend", extend.toJSONString());
        //request.setUserData(userData.toJSONString());
        return client.getAcsResponse(request);
    }

    /**
     * 批量获取视频信息函数
     *
     * @return GetVideoInfosResponse 获取视频信息响应数据
     * @throws Exception
     */
    public GetVideoInfosResponse getVideoInfos(List<String> videoIds) throws Exception {
        DefaultAcsClient client = this.initVodClient(videoProperties.getAccessKeyId(), videoProperties.getAccessKeySecret());
        GetVideoInfosRequest request = new GetVideoInfosRequest();
        String requestParam = createRequestParam(videoIds);
        request.setVideoIds(requestParam);
        return client.getAcsResponse(request);
    }

    /**
     * description: getVideoCoverUrl
     * 获取视频封面
     * version: 1.0
     * date: 2020/8/2 21:46
     * author: 崔益翔
     *
     * @param videoId
     * @return java.lang.String
     */
    public String getVideoCoverUrl(String videoId) {
        String coverURL = null;
        try {
            //            获取视频信息
            GetPlayInfoResponse videoInfoResponse = this.getPlayInfo(videoId);
            GetPlayInfoResponse.VideoBase videoBase = videoInfoResponse.getVideoBase();
//            封面图片
            coverURL = videoBase.getCoverURL();
        } catch (Exception e) {
            logger.error("获取视频封面信息失败");
            e.printStackTrace();
        }
        return coverURL;
    }

    /**
     * description: getVideoPlayUrlAndCover
     * 获取视频播放地址和封面
     * version: 1.0
     * date: 2020/8/2 21:46
     * author: 崔益翔
     *
     * @param videoId
     * @return VideoInfo
     */
    public VideoInfo getVideoPlayUrlAndCover(String videoId) {
        GetPlayInfoResponse videoInfoResponse = null;
        try {
            videoInfoResponse = this.getPlayInfo(videoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == videoInfoResponse) {
            logger.error("获取视频信息失败");
            return new VideoInfo();
        }
        GetPlayInfoResponse.VideoBase videoBase = videoInfoResponse.getVideoBase();
        //     封面图片
        String coverURL = videoBase.getCoverURL();
        List<GetPlayInfoResponse.PlayInfo> playInfoList = videoInfoResponse.getPlayInfoList();
//      播放url
        String url = playInfoList.get(0).getPlayURL();

        return new VideoInfo().setVideoId(videoId).setCoverUrl(coverURL).setUrl(url);
    }
}
