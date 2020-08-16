package com.dw.vod.starter.pojo.vod.dto;

import java.io.File;
import java.io.InputStream;

/**
 * @author CuiYiXiang
 * @Classname FileInputStreamDto
 * @Description TODO
 * @Date 2020/8/2
 */
public class FileInputStreamDto {
    private InputStream inputStream;
    private File file;

    public FileInputStreamDto() {
    }

    public FileInputStreamDto(InputStream inputStream, File file) {
        this.inputStream = inputStream;
        this.file = file;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public FileInputStreamDto setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public File getFile() {
        return file;
    }

    public FileInputStreamDto setFile(File file) {
        this.file = file;
        return this;
    }
}
