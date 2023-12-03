package com.wonder.bjutcats;

import com.wonder.bjutcats.mapper.ThumbMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BjuTcatsApplicationTests {

    @Autowired
    private ThumbMapper tm;

    @Test
    void contextLoads() {

        System.out.println(tm.countThumbCat(0));

    }

}
