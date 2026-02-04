package com.tlias.paper0_1.controller;

import com.tlias.paper0_1.entity.Content;
import com.tlias.paper0_1.entity.Content_Result;
import com.tlias.paper0_1.entity.HotVideoResponse;
import com.tlias.paper0_1.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 推荐控制器类
 * 提供视频推荐相关的REST API接口
 * 处理前端对热门视频等推荐内容的查询请求
 */
@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

    /**
     * 内容服务对象
     * 通过Spring的@Autowired注解自动注入ContentService实例
     * 用于处理与视频内容相关的业务逻辑
     */
    @Autowired
    private ContentService contentService;

    /**
     * 获取热门视频
     * HTTP GET请求 /api/recommend/hot
     * 返回按点赞数排序的热门视频列表
     * 
     * @param currentPage 当前页码，默认为1
     * @param pageSize 每页大小，默认为10
     * @return 热门视频响应对象，格式为{"code": 200, "data": {"videos": [...], "hasMore": true}}
     */
    @GetMapping("/hot")
    public Content_Result<HotVideoResponse> getHotVideos(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        HotVideoResponse hotVideoResponse = contentService.getHotVideos(currentPage, pageSize);
        return Content_Result.success("获取热门视频成功", hotVideoResponse);
    }
}