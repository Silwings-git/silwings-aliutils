package com.dw.img.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

/**
 * @author CuiYiXiang
 * @Classname CompressLevelMappingProperties
 * @Description TODO
 * @Date 2020/8/2
 */
@ConfigurationProperties(prefix = "alicustom.compresslevel")
public class CompressLevelMappingProperties {
    /**
     * 缩放比例,值越大压缩后的图片越大
     * 如果大于1图片将放大
     */
    private List<String> scale;

    /**
     * 压缩等级映射(不提供配置)
     */
    private HashMap<Integer, Float> scaleMap;

    /**
     * 压缩图片时的默认压缩比例
     */
    private String defaultCompressValue = "0.6";

    public String getDefaultCompressValue() {
        return defaultCompressValue;
    }

    public void setDefaultCompressValue(String defaultCompressValue) {
        this.defaultCompressValue = defaultCompressValue;
    }

    public List<String> getScale() {
        return scale;
    }

    public void setScale(List<String> scale) {
        this.scale = scale;
    }

    /**
     * description: 根据等级获取压缩值(比例)
     * version: 1.0
     * date: 2020/8/2 16:51
     * author: 崔益翔
     *
     * @param level 等级
     * @return java.lang.Float 压缩比例
     */
    public Float getCompressLevelValue(Integer level) {
        if (null == scaleMap) {
            createScaleMap(getScale());
        }
        Float compressLevelValue = scaleMap.get(level);
        if (null == compressLevelValue) {
            compressLevelValue = scaleMap.get(0);
        }
        return compressLevelValue;
    }

    /**
     * description: 初始化压缩等级映射
     * version: 1.0
     * date: 2020/8/2 16:50
     * author: 崔益翔
     *
     * @param scale
     * @return void
     */
    private void createScaleMap(List<String> scale) {
        int level = 0;
        scaleMap = new HashMap<>(16);
        if (null == scale || scale.size() == 0) {
            scaleMap.put(level, Float.valueOf(defaultCompressValue));
        } else {
            for (String sc : scale) {
                scaleMap.put(level++, Float.valueOf(sc));
            }
        }
    }
}
