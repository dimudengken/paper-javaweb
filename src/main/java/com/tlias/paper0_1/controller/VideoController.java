package com.tlias.paper0_1.controller;

import com.tlias.paper0_1.entity.Content;
import com.tlias.paper0_1.entity.RandomVideo_Result;
import com.tlias.paper0_1.entity.Content_Result;
import com.tlias.paper0_1.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private ContentService contentService;

    /**
     * 获取游客随机推荐视频
     */
    @GetMapping("/cumulative/visitor")
    public RandomVideo_Result<List<Content>> getRandomVideos(
            @RequestParam(defaultValue = "10") Integer count,
            @RequestParam(required = false) String vids) {
        
        // 解析排除的视频ID列表
        List<String> excludeIds = null;
        if (vids != null && !vids.isEmpty()) {
            excludeIds = Arrays.asList(vids.split(","));
        }
        
        // 调用 Service 获取随机视频列表
        List<Content> list = contentService.getRandomVideos(count, excludeIds);
        
        // 判断是否还有更多数据：排除指定ID后，看是否还有额外的记录
        long remainingCount = contentService.getTotalVideoCount(excludeIds);
        boolean hasMore = remainingCount > list.size();
        
        return RandomVideo_Result.build(200, "获取随机视频成功", list, hasMore);
    }
    
    /**
     * 获取随机视频（不排重，使用Content_Result返回）
     */
    @GetMapping("/random/visitor")
    public Content_Result<List<Content>> getRandomVideosSimple(
            @RequestParam(defaultValue = "11") Integer count) {
        
        // 调用 Service 获取随机视频列表（不排重）
        List<Content> list = contentService.getRandomVideosWithoutExclude(count);
        
        return Content_Result.success("获取随机视频成功", list);
    }
}