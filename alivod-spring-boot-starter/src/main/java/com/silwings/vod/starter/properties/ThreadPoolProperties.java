package com.silwings.vod.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author CuiYiXiang
 * @Classname ThreadPoolProperties
 * @Description 线程池配置
 * @Date 2020/7/31
 */
@ConfigurationProperties(prefix = "alicustom.threadpool")
public class ThreadPoolProperties {
    /**
     * 设置核心线程数
     */
    private int corePoolSize = 5;


    /**
     * 设置最大线程数
     */
    private int maxPoolSize = 10;


    /**
     * 设置队列容量
     */
    private int queueCapacity = 20;

    /**
     * 设置线程活跃时间（秒）
     */
    private int keepAliveSeconds = 60;

    /**
     * 设置默认线程名称
     */
    private String threadNamePrefix = "Ali-Video-Thread-";


    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }
}
