package com.tlias.paper0_1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Favorite {

    // 用户收藏视频字段
    private String user_id;
    private String video_id;
    private String create_time;
}
