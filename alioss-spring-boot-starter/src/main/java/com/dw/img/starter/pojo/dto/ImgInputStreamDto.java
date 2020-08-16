package com.dw.img.starter.pojo.dto;

import java.io.File;
import java.io.InputStream;

/**
 * @author CuiYiXiang
 * @Classname ImgInputStreamDto
 * @Description TODO
 * @Date 2020/8/2
 */
public class ImgInputStreamDto {
    public InputStream inputStream;
    public File file;

    public ImgInputStreamDto() {
    }

    public ImgInputStreamDto(InputStream inputStream, File file) {
        this.inputStream = inputStream;
        this.file = file;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public ImgInputStreamDto setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public File getFile() {
        return file;
    }

    public ImgInputStreamDto setFile(File file) {
        this.file = file;
        return this;
    }
}
