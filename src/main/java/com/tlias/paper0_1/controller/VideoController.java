package com.tlias.paper0_1.controller;

import com.tlias.paper0_1.entity.Content;
import com.tlias.paper0_1.entity.RandomVideo_Result;
import com.tlias.paper0_1.entity.Content_Result;
import com.tlias.paper0_1.entity.PageResult;
import com.tlias.paper0_1.service.ContentService;
import com.tlias.paper0_1.service.UserLikesService;
import com.tlias.paper0_1.tools.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private ContentService contentService;
    
    @Autowired
    private UserLikesService userLikesService;

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
    
    /**
     * 根据视频ID获取单个视频信息
     * HTTP GET请求 /api/video/getone?vid={视频ID}
     * 
     * @param vid 视频ID
     * @return Content_Result<Content> 单个视频信息结果
     */
    @GetMapping("/getone")
    public Content_Result<Content> getVideoById(@RequestParam("vid") String vid) {
        // 调用 Service 根据 ID获取视频信息
        Content video = contentService.getContentById(vid);
        
        if (video != null) {
            return Content_Result.success("获取视频成功", video);
        } else {
            return Content_Result.error("视频不存在");
        }
    }
    
    /**
     * 获取收藏夹中的视频列表
     * HTTP GET 请求 /api/video/user-collect
     */
    @GetMapping("/user-collect")
    public Content_Result<PageResult<Content>> getFavoriteVideos(
            @RequestParam("fid") Long fid,
            @RequestParam(value = "rule", defaultValue = "1") Integer rule,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "quantity", defaultValue = "10") Integer quantity) {
        
        PageResult<Content> result = contentService.getFavoriteVideos(fid, rule, page, quantity);
        
        if (result != null && result.getItems() != null) {
            return Content_Result.success("获取收藏夹视频成功", result);
        } else {
            return Content_Result.error("获取收藏夹视频失败");
        }
    }
    
    /**
     * 处理视频点赞/点踩
     * HTTP POST 请求 /api/video/love-or-not
     */
    @PostMapping("/love-or-not")
    public Content_Result<?> handleLoveOrNot(
            @RequestHeader("Authorization") String authorization,
            @RequestBody LoveOrNotRequest request) {
        
        // 从 token 中获取用户 ID
        String token = authorization.replace("Bearer ", "");
        Claims claims = JwtUtil.parseToken(token);
        String userId = claims.get("id").toString();
        
        String videoId = request.getVideoId();
        String action = request.getAction();
        
        boolean success = false;
        String message = "";
        
        if ("like".equals(action)) {
            success = userLikesService.handleLike(userId, videoId, true);
            message = success ? "点赞成功" : "点赞失败";
        } else if ("unlike".equals(action)) {
            success = userLikesService.handleLike(userId, videoId, false);
            message = success ? "取消点赞成功" : "取消点赞失败";
        } else {
            return Content_Result.error("无效的操作类型");
        }
        
        if (success) {
            return Content_Result.success(message);
        } else {
            return Content_Result.error(message);
        }
    }
    
    /**
     * 检查用户是否已点赞视频
     * HTTP GET 请求 /api/video/love/check
     */
    @GetMapping("/love/check")
    public Content_Result<Boolean> checkLove(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("videoId") String videoId) {
        
        // 从 token 中获取用户 ID
        String token = authorization.replace("Bearer ", "");
        Claims claims = JwtUtil.parseToken(token);
        String userId = claims.get("id").toString();
        
        // 检查是否已点赞
        boolean isLiked = userLikesService.isLiked(userId, videoId);
        
        return Content_Result.success("检查成功", isLiked);
    }
    
    // 内部请求类定义
    public static class LoveOrNotRequest {
        private String videoId;
        private String action;
        
        public String getVideoId() {
            return videoId;
        }
        
        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }
        
        public String getAction() {
            return action;
        }
        
        public void setAction(String action) {
            this.action = action;
        }
    }
}