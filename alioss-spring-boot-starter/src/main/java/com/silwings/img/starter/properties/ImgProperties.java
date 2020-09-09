package com.silwings.img.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author CuiYiXiang
 * @Classname ImgProperties
 * @Description TODO
 * @Date 2020/8/2
 */
@ConfigurationProperties(prefix = "alicustom.aliimg")
public class ImgProperties {

    /**
     * 对象存储endpoint
     */
    private String endpoint;

    /**
     * 阿里秘钥
     */
    private String accessKeyId;

    /**
     * 阿里秘钥
     */
    private String accessKeySecret;

    /**
     * 存储空间
     */
    private String bucketName;

    /**
     * 是否压缩图片,默认不压缩
     */
    private boolean compress = false;

    public boolean isCompress() {
        return compress;
    }

    public void setCompress(boolean compress) {
        this.compress = compress;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
