package com.tlias.paper0_1.service.impl;

import com.tlias.paper0_1.entity.Content;
import com.tlias.paper0_1.entity.Content_Result;
import com.tlias.paper0_1.entity.HotVideoResponse;
import com.tlias.paper0_1.entity.PageResult;
import com.tlias.paper0_1.mapper.ContentMapper;
import com.tlias.paper0_1.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容服务实现类
 * 实现ContentService接口，提供视频内容相关的业务逻辑处理
 * 通过依赖注入使用ContentMapper进行数据访问操作
 */
@Service
public class ContentServiceImpl implements ContentService {

    /**
     * 内容数据访问对象
     * 通过Spring的@Autowired注解自动注入ContentMapper实例
     * 用于执行与视频内容数据相关的数据库操作
     */
    @Autowired
    private ContentMapper contentMapper;

    /**
     * 获取所有视频内容信息
     * 调用数据访问层方法获取全部视频内容数据
     * 
     * @return 包含所有视频内容信息的列表
     */
    @Override
    public List<Content> getAllContents() {
        return contentMapper.getAllContents();
    }

    /**
     * 根据ID获取单个视频内容信息
     * 通过数据访问层根据ID查询特定的视频内容信息
     * 
     * @param id 要查询的视频内容ID
     * @return 对应ID的视频内容对象，如果不存在则返回null
     */
    @Override
    public Content getContentById(String id) {
        return contentMapper.getContentById(id);
    }

    /**
     * 分页获取视频内容
     * 根据当前页码和页面大小获取指定范围的视频内容数据
     * 
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果，包含当前页数据和分页信息
     */
    @Override
    public PageResult<Content> getContentByPage(int currentPage, int pageSize) {
        // 计算偏移量
        int offset = (currentPage - 1) * pageSize;
        
        // 查询当前页数据
        List<Content> contents = contentMapper.getContentByPage(offset, pageSize);
        
        // 查询总记录数
        long totalCount = contentMapper.getContentCount();
        
        // 计算总页数
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        
        // 创建分页结果
        PageResult<Content> result = new PageResult<>();
        result.setItems(contents);
        result.setTotal(totalCount);
        result.setPageSize(pageSize);
        result.setCurrentPage(currentPage);
        result.setTotalPages(totalPages);
        result.setHasNextPage(currentPage < totalPages);
        
        return result;
    }

    /**
     * 根据标题模糊查询视频内容
     * 通过数据访问层根据标题关键词进行模糊查询
     * 
     * @param title 标题关键词
     * @return 匹配标题关键词的视频内容列表
     */
    @Override
    public List<Content> getContentByTitle(String title) {
        return contentMapper.getContentByTitle(title);
    }

    /**
     * 根据标题分页查询视频内容
     * 根据标题关键词进行模糊查询并按分页参数返回结果
     * 
     * @param title 标题关键词
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果，包含当前页数据和分页信息
     */
    @Override
    public PageResult<Content> getContentByTitleAndPage(String title, int currentPage, int pageSize) {
        // 计算偏移量
        int offset = (currentPage - 1) * pageSize;
        
        // 查询当前页数据
        List<Content> contents = contentMapper.getContentByTitleAndPage(title, offset, pageSize);
        
        // 查询总记录数
        long totalCount = contentMapper.getContentCountByTitle(title);
        
        // 计算总页数
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        
        // 创建分页结果
        PageResult<Content> result = new PageResult<>();
        result.setItems(contents);
        result.setTotal(totalCount);
        result.setPageSize(pageSize);
        result.setCurrentPage(currentPage);
        result.setTotalPages(totalPages);
        result.setHasNextPage(currentPage < totalPages);
        
        return result;
    }
    
    @Override
    public Content_Result<Void> likeVideo(String videoId) {
        try {
            int rowsAffected = contentMapper.updateLikedCount(videoId, 1);
            if (rowsAffected > 0) {
                return Content_Result.success("点赞成功");
            } else {
                return Content_Result.error("点赞失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Content_Result.error("点赞发生异常: " + e.getMessage());
        }
    }
    
    @Override
    public Content_Result<Void> dislikeVideo(String videoId) {
        try {
            int rowsAffected = contentMapper.updateDislikedCount(videoId, 1);
            if (rowsAffected > 0) {
                return Content_Result.success("不喜欢成功");
            } else {
                return Content_Result.error("不喜欢失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Content_Result.error("不喜欢发生异常: " + e.getMessage());
        }
    }
    
    @Override
    public Content_Result<Void> unlikeVideo(String videoId) {
        try {
            int rowsAffected = contentMapper.updateLikedCount(videoId, -1);
            if (rowsAffected > 0) {
                return Content_Result.success("取消点赞成功");
            } else {
                return Content_Result.error("取消点赞失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Content_Result.error("取消点赞发生异常: " + e.getMessage());
        }
    }
    
    @Override
    public Content_Result<Void> undislikeVideo(String videoId) {
        try {
            int rowsAffected = contentMapper.updateDislikedCount(videoId, -1);
            if (rowsAffected > 0) {
                return Content_Result.success("取消不喜欢成功");
            } else {
                return Content_Result.error("取消不喜欢失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Content_Result.error("取消不喜欢发生异常: " + e.getMessage());
        }
    }
    
    @Override
    public String getVideoUrl(String videoId) {
        Content content = contentMapper.getContentById(videoId);
        return content != null ? content.getVideo_url() : null;
    }
    
    @Override
    public String getVideoCoverUrl(String videoId) {
        Content content = contentMapper.getContentById(videoId);
        return content != null ? content.getVideo_cover_url() : null;
    }
    
    @Override
    public HotVideoResponse getHotVideos(int currentPage, int pageSize) {
        // 确保页码至少为1
        int actualPage = Math.max(1, currentPage);
        
        // 计算偏移量
        int offset = (actualPage - 1) * pageSize;
        
        // 查询热门视频数据
        List<Content> hotVideos = contentMapper.getHotVideos(offset, pageSize);
        
        // 查询总记录数用于判断是否有更多数据
        long totalCount = contentMapper.getContentCount();
        
        // 计算当前页结束位置
        int endIndex = actualPage * pageSize;
        
        // 判断是否还有更多数据
        boolean hasMore = endIndex < totalCount;
        
        // 返回热门视频响应对象
        return new HotVideoResponse(hotVideos, hasMore);
    }
}