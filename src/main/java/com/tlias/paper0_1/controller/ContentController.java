package com.tlias.paper0_1.controller;

import com.tlias.paper0_1.entity.Content;
import com.tlias.paper0_1.entity.Content_Result;
import com.tlias.paper0_1.entity.PageResult;
import com.tlias.paper0_1.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 内容控制器类
 * 提供视频内容相关的REST API接口
 * 处理前端对视频内容数据的查询请求
 */
@RestController
@RequestMapping("/contents")
public class ContentController {

    /**
     * 内容服务对象
     * 通过Spring的@Autowired注解自动注入ContentService实例
     * 用于处理与视频内容相关的业务逻辑
     */
    // 注意：实际注入的是impl包下的ContentServiceImpl
    @Autowired
    private ContentService contentService;

    /**
     * 获取所有视频内容信息
     * HTTP GET请求 /contents
     * 返回所有视频内容的列表数据
     * 
     * @return 包含所有视频内容信息的列表
     */
    @GetMapping
    public List<Content> getAllContents() {
        return contentService.getAllContents();
    }

    /**
     * 根据ID获取单个视频内容信息
     * HTTP GET请求 /contents/{id}
     * 通过路径变量获取指定ID的视频内容信息
     * 
     * @param id 要查询的视频内容ID，从URL路径中获取
     * @return 对应ID的视频内容对象
     */
    @GetMapping("/{id}")
    public Content getContentById(@PathVariable String id) {
        return contentService.getContentById(id);
    }

    /**
     * 分页获取视频内容
     * HTTP GET请求 /contents/page
     * 支持分页查询视频内容，便于前端实现懒加载功能
     * 
     * @param currentPage 当前页码，默认为1
     * @param pageSize 每页大小，默认为10
     * @return 分页结果，包含当前页数据和分页信息
     */
    @GetMapping("/page")
    public PageResult<Content> getContentByPage(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return contentService.getContentByPage(currentPage, pageSize);
    }

    /**
     * 根据标题模糊查询视频内容
     * HTTP GET请求 /contents/search
     * 根据标题关键词进行模糊查询
     * 
     * @param title 标题关键词
     * @return 匹配标题关键词的视频内容列表
     */
    @GetMapping("/search")
    public List<Content> getContentByTitle(@RequestParam String title) {
        return contentService.getContentByTitle(title);
    }

    /**
     * 根据标题分页查询视频内容
     * HTTP GET请求 /contents/search/page
     * 支持根据标题关键词进行模糊查询并分页返回结果
     * 
     * @param title 标题关键词
     * @param currentPage 当前页码，默认为1
     * @param pageSize 每页大小，默认为10
     * @return 分页结果，包含当前页数据和分页信息
     */
    @GetMapping("/search/page")
    public PageResult<Content> getContentByTitleAndPage(
            @RequestParam String title,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return contentService.getContentByTitleAndPage(title, currentPage, pageSize);
    }
    
    /**
     * 点赞视频
     * HTTP POST请求 /contents/{id}/like
     * 为指定ID的视频增加点赞数量
     * 
     * @param id 要点赞的视频ID
     * @return Content_Result<Void> 操作结果
     */
    @PostMapping("/{id}/like")
    public Content_Result<Void> likeVideo(@PathVariable String id) {
        return contentService.likeVideo(id);
    }
    
    /**
     * 不喜欢视频
     * HTTP POST请求 /contents/{id}/dislike
     * 为指定ID的视频增加不喜欢数量
     * 
     * @param id 要不喜欢的视频ID
     * @return Content_Result<Void> 操作结果
     */
    @PostMapping("/{id}/dislike")
    public Content_Result<Void> dislikeVideo(@PathVariable String id) {
        return contentService.dislikeVideo(id);
    }
    
    /**
     * 取消点赞视频
     * HTTP DELETE请求 /contents/{id}/like
     * 为指定ID的视频减少点赞数量
     * 
     * @param id 要取消点赞的视频ID
     * @return Content_Result<Void> 操作结果
     */
    @DeleteMapping("/{id}/like")
    public Content_Result<Void> unlikeVideo(@PathVariable String id) {
        return contentService.unlikeVideo(id);
    }
    
    /**
     * 取消不喜欢视频
     * HTTP DELETE请求 /contents/{id}/dislike
     * 为指定ID的视频减少不喜欢数量
     * 
     * @param id 要取消不喜欢的视频ID
     * @return Content_Result<Void> 操作结果
     */
    @DeleteMapping("/{id}/dislike")
    public Content_Result<Void> undislikeVideo(@PathVariable String id) {
        return contentService.undislikeVideo(id);
    }
    
    /**
     * 获取视频播放地址
     * HTTP GET请求 /contents/{id}/video-url
     * 为前端提供指定视频的播放地址
     * 
     * @param id 视频ID
     * @return 视频播放地址字符串
     */
    @GetMapping("/{id}/video-url")
    public String getVideoUrl(@PathVariable String id) {
        return contentService.getVideoUrl(id);
    }
    
    /**
     * 获取视频封面地址
     * HTTP GET请求 /contents/{id}/cover-url
     * 为前端提供指定视频的封面地址，用于显示封面图片
     * 
     * @param id 视频ID
     * @return 视频封面地址字符串
     */
    @GetMapping("/{id}/cover-url")
    public String getVideoCoverUrl(@PathVariable String id) {
        return contentService.getVideoCoverUrl(id);
    }
    
    /**
     * 获取完整视频信息（包括封面和视频链接）
     * HTTP GET请求 /contents/{id}/detail
     * 返回包含封面和视频链接的完整视频信息
     * 
     * @param id 视频ID
     * @return 完整的视频内容对象
     */
    @GetMapping("/{id}/detail")
    public Content getVideoDetail(@PathVariable String id) {
        return contentService.getContentById(id);
    }
}