package com.tlias.paper0_1.service;

import com.tlias.paper0_1.entity.Content;
import com.tlias.paper0_1.entity.Content_Result;
import com.tlias.paper0_1.entity.HotVideoResponse;
import com.tlias.paper0_1.entity.PageResult;

import java.util.List;

public interface ContentService {
    // 查询所有视频内容
    List<Content> getAllContents();

    // 根据ID查询视频内容
    Content getContentById(String id);

    // 分页查询视频内容
    PageResult<Content> getContentByPage(int currentPage, int pageSize);

    // 根据标题模糊查询视频内容
    List<Content> getContentByTitle(String title);

    // 根据标题分页查询视频内容
    PageResult<Content> getContentByTitleAndPage(String title, int currentPage, int pageSize);

    // 点赞功能 - 增加视频的点赞数量
    Content_Result<Void> likeVideo(String videoId);

    // 不喜欢功能 - 增加视频的不喜欢数量
    Content_Result<Void> dislikeVideo(String videoId);

    // 取消点赞功能 - 减少视频的点赞数量
    Content_Result<Void> unlikeVideo(String videoId);

    // 取消不喜欢功能 - 减少视频的不喜欢数量
    Content_Result<Void> undislikeVideo(String videoId);
    
    // 获取视频播放地址
    String getVideoUrl(String videoId);
    
    // 获取视频封面地址
    String getVideoCoverUrl(String videoId);
    
    // 获取热门视频
    HotVideoResponse getHotVideos(int currentPage, int pageSize);

    //获取随机视频
    List<Content> getRandomVideos(int count, List<String> excludeIds);
    
    //获取视频总记录数（用于判断是否有更多数据）
    long getTotalVideoCount(List<String> excludeIds);
    
    //获取随机视频（不排重）
    List<Content> getRandomVideosWithoutExclude(int count);
}