package com.tlias.paper0_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 热门视频API响应类
 * 用于封装热门视频API的响应格式
 * 格式：{"code": 200, "data": {"videos": [...], "hasMore": true}}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotVideoResponse {
    
    /**
     * 视频列表
     */
    private List<Content> videos;
    
    /**
     * 是否还有更多数据
     */
    private Boolean hasMore;
    
    /**
     * 构造函数，接收视频列表和是否有更多数据
     * @param videos 视频列表
     * @param hasMore 是否还有更多数据
     */
    public HotVideoResponse(List<Content> videos, boolean hasMore) {
        this.videos = videos;
        this.hasMore = hasMore;
    }
}