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
    private Integer gender;
    private String color;
    private Integer status;
    private Integer campus;
    private String location;
    private String detail;
    private String imageurl;

}
