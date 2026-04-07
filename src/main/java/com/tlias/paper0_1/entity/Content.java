package com.tlias.paper0_1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Content {
    private String video_id;
    private String video_type;
    private String title;
    private String description;
    private String create_time;
    private String author_id;
    private String nick_name;
//    private String avatar;
    private String video_url;
    private String video_cover_url;
    private long liked_count;
    private long disliked_count;
    private long video_play_count;
    private long video_share_count;
    private long video_favorite_count;
    private long video_coin_count;
    private String status;
    private String source_keyword;


}
