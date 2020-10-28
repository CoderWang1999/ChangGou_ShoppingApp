package com.changgou.file.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.file.pojo.FastDFSFile;
import com.changgou.file.util.FastDFSUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping("/upload")
    public Result uploadFile(@RequestParam(value = "file") MultipartFile  file){
        try {
            //判断文件是否存在
             if (file==null){
                throw  new RuntimeException("文件不存在");
            }
            //获取文件完整名称
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isEmpty(originalFilename)){
                throw new RuntimeException("文件不存在");
            }
            //获取文件的扩展名
            String exName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //获取器文件内容
            byte[] content = file.getBytes();
            //创建fastDFS实体类对象
            FastDFSFile fastDFSFile = new FastDFSFile(originalFilename, content, exName);
            //使用工具类上传文件
            String[] uploadResult = FastDFSUtil.upload(fastDFSFile);
            //拼接文件URL
            String url = FastDFSUtil.getTrackerUrl()+uploadResult[0]+"/"+uploadResult[1];
            return new Result(true,StatusCode.OK,"文件上传成功",url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, StatusCode.ERROR,"文件上传失败");
    }
}
