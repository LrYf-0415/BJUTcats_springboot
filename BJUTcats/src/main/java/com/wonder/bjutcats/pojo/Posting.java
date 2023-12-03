package com.wonder.bjutcats.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posting {

    private Integer id;
    private Integer userid;
    private Integer catid;
    private String content;

}
