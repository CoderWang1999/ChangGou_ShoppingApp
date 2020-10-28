package com.changgou.file.util;

import com.changgou.file.pojo.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class FastDFSUtil {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(FastDFSUtil.class);

    static {
        try {
            //拿到ClassPath下的配置文件路径
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getPath();
            //加载Tracker链接信息
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail!", e);
        }
    }

    //文件上传
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {
        //获取文件的作者
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", fastDFSFile.getAuthor());
        //接收返回数据
        String[] uploadResults = null;
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            //通过StorageClient访问Stora实现文件上传，并且获取文件上传后的存储信息
            uploadResults = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
        } catch (Exception e) {
            logger.error("Exception when uploadind the file:" + fastDFSFile.getName(), e);
            ;
        }
        if (uploadResults == null && storageClient != null) {
            logger.error("upload file fail, error code:" + storageClient.getErrorCode());
        }
        return uploadResults;
    }

    //获取文件信息
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getStorageClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            logger.error("Exception: Get File fron FastDFS failed", e);
        }
        return null;
    }


    //文件下载
    public static InputStream downloadFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getStorageClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            InputStream ins = new ByteArrayInputStream(fileByte);
            return ins;
        } catch (Exception e) {
            logger.error("Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    //文件删除
    public static void deleteFile(String groupName, String remoteFileName) throws Exception {
        StorageClient storageClient = getStorageClient();
        storageClient.delete_file(groupName, remoteFileName);
    }

    //获取storage组
    public static StorageServer[] getStoreStorages(String groupName)
            throws Exception {
        //创建TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        //获取TrackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取Storage组
        return trackerClient.getStoreStorages(trackerServer, groupName);
    }


    //获取Storage信息,IP和端口
    public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = getTrackerServer();
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }


    //获取Tracker服务地址
    public static String getTrackerUrl() throws Exception {
        return "http://" + getTrackerServer().getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port() + "/";
    }

    //获取storageClient
    public static StorageClient getStorageClient() throws Exception {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    //获取trackerService
    public static TrackerServer getTrackerServer() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }
}
