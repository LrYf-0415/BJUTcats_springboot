package com.wonder.bjutcats.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 用户openid和sessionkey的载体
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken{
    private String openid;
    private String session_key;
}
