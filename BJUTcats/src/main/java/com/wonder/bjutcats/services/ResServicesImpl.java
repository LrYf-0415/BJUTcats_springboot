package com.wonder.bjutcats.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// 接收imageurl将服务器本地图片转为字节形式返回
@Component
public class ResServicesImpl implements ResServices{

    public byte[] ReqImage(String imagePath) throws IOException {
        // 读取图片文件的字节数据
        Path path = Paths.get(imagePath);
        byte[] bytes = Files.readAllBytes(path);

        // 返回字节数据
        return bytes;
    }

}
