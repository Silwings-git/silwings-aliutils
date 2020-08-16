package com.dw.vod.starter.config;

import com.dw.vod.starter.properties.ThreadPoolProperties;
import com.dw.vod.starter.properties.VideoProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author CuiYiXiang
 * @Classname ThreadPoolConfig
 * @Description TODO
 * @Date 2020/7/31
 */
@Configuration
@EnableConfigurationProperties({ThreadPoolProperties.class, VideoProperties.class})
@ConditionalOnProperty(
        prefix = "alicustom",
        name = "openvod",
        havingValue = "true"
)
@EnableAsync
public class ThreadPoolConfig {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolConfig.class);


    @Autowired
    private ThreadPoolProperties threadPoolProperties;

    @Autowired
    private VideoProperties videoProperties;

    @Bean(name = "vodTaskExecutor")
    public TaskExecutor vodTaskExecutor() {
        if (videoProperties.isSyncUpload()) {
            return null;
        }
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        // 设置最大线程数
        executor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        // 设置队列容量
        executor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
        // 设置默认线程名称
        executor.setThreadNamePrefix(threadPoolProperties.getThreadNamePrefix());
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        logger.info("线程池初始化完成");
        return executor;
    }
}
