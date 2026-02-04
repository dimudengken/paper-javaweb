package com.tlias.paper0_1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private String video_id;
    private String video_type;
    private String title;
    private String description;
    private String create_time;
    private String user_id;
    private String nickname;
    private String avatar;
    private String video_url;
    private String video_cover_url;
    private long liked_count;
    private long disliked_count;
    private long video_play_count;


}
