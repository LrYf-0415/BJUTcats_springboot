package com.wonder.bjutcats.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {

    private Integer id;         // 小猫id，数据库中为自动生成
    private String name;        // 小猫名称
    private Integer gender;     // 0表示母，1表示公，2表示未知
    private String color;       // 猫的毛色
    private Integer status;     // 0表示不在校，1表示在校
    private Integer campus;     // 0表示校本部，1表示通州校区，2表示中蓝公寓
    private String location;    // 猫的位置
    private String detail;      // 猫的详细信息
    private String imageurl;    // 猫图片的url地址

}
