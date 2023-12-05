package com.wonder.bjutcats.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {

    private Integer id;
    private String name;
    private Integer gender;     // 0表示母，1表示公
    private String color;
    private Integer status;     // 0表示不在校，1表示在校
    private Integer campus;     // 0表示校本部，1表示通州校区，2表示中蓝公寓
    private String location;
    private String detail;
    private String imageurl;

}
