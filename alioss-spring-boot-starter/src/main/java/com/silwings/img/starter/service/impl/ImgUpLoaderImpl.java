package com.silwings.img.starter.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.silwings.img.starter.properties.ImgProperties;
import com.silwings.img.starter.service.ImgUpLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.List;

/**
 * @author CuiYiXiang
 * @Classname ImgUpLoaderImpl
 * @Description TODO
 * @Date 2020/8/2
 */
public class ImgUpLoaderImpl implements ImgUpLoader {

    @Autowired
    private ImgProperties imgProperties;

    @Override
    public String upFile(InputStream inputStream, String fileName) throws RuntimeException{
        OSSClient ossClient = initOssClient();
        String bucketName = imgProperties.getBucketName();
        String url = "";

        try {
            // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
            String fileKey = fileName;

            ossClient.putObject(bucketName, fileKey, inputStream);
            BucketInfo info = ossClient.getBucketInfo(bucketName);
            url = fileKey;
//            url= "http://img.bangxiaoer.top/"+fileKey;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ossClient.shutdown();
        }
        return url;
    }

    /**
     * description: 初始化客户端
     * version: 1.0
     * date: 2020/8/2 16:57
     * author: 崔益翔
     * @param
     * @return com.aliyun.oss.OSSClient
     */
    public OSSClient initOssClient() {
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        return new OSSClient(imgProperties.getEndpoint(), imgProperties.getAccessKeyId(), imgProperties.getAccessKeySecret());
    }

    @Override
    public boolean delete(List<String> imgs) {
        OSSClient ossClient = initOssClient();
        try {
            // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(imgProperties.getBucketName()).withKeys(imgs));
//            获取已删除的文件信息
//            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            return true;
        } catch (OSSException oe) {
            oe.printStackTrace();
            return false;
        } catch (ClientException ce) {
            ce.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ossClient.shutdown();
        }
    }

}
