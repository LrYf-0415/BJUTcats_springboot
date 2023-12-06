package com.wonder.bjutcats.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;           // 即微信用户的OpenID
    private String username;     // 用户名
    private Integer gender;      // 用户性别：0表示女，1表示男，2表示保密
    private String emails;       // 用户邮箱
    private String phone;        // 用户手机号
    private String token;        // 用户登录时从微信api获取的SessionKey
    private String imageurl;     // 用户头像图片的url地址

}
