package com.wonder.bjutcats.services;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ResServices {

    public byte[] ReqImage(String imagePath) throws IOException;

}
