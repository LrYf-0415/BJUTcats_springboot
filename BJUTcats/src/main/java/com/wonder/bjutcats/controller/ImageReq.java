package com.wonder.bjutcats.controller;

import com.wonder.bjutcats.services.ResServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// 响应 -> 使用url获取服务器图片的请求
@RestController
public class ImageReq {

    @Autowired
    private ResServices resServices;

    @RequestMapping(value = "/storage/image/{serv}/{name}/{filename}" , method = RequestMethod.GET)
    public ResponseEntity<byte []> ReqImage(@PathVariable String serv ,
                                            @PathVariable String name ,
                                            @PathVariable String filename) throws IOException {
        // 构件服务器存储图片路径
        String imagePath = Paths.get("D:\\Files\\Programing\\BJUTcats\\storage\\image", serv, name, filename).toString();


        // 读取图片文件
        byte[] bytes = resServices.ReqImage(imagePath);

        // 错误响应
        if(bytes == null){
            return ResponseEntity.notFound().build();
        }

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(bytes.length);

        // 返回图片数据
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bytes);

    }

}
