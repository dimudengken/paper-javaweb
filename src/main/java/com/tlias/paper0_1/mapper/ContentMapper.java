package com.tlias.paper0_1.mapper;

import com.tlias.paper0_1.entity.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 内容数据访问层接口
 * 定义了对视频内容数据的各种数据库操作方法
 * SQL语句在对应的XML映射文件中定义
 */
@Mapper
public interface ContentMapper {

    /**
     * 查询所有视频内容信息
     * 从video_contents表中获取全部视频内容数据
     *
     * @return 包含所有视频内容信息的列表
     */
    List<Content> getAllContents();

    /**
     * 根据ID查询单个视频内容信息
     * 从video_contents表中根据video_id字段精确查询单条记录
     *
     * @param id 要查询的视频内容ID
     * @return 对应ID的视频内容对象，如果不存在则返回null
     */
    Content getContentById(String id);

    /**
     * 分页查询视频内容
     * 根据偏移量和页面大小查询指定范围的视频内容数据
     *
     * @param offset 偏移量，从0开始
     * @param size   页面大小，即每页返回多少条记录
     * @return 视频内容列表
     */
    List<Content> getContentByPage(@Param("offset") int offset, @Param("size") int size);

    /**
     * 查询视频内容总数
     * 获取video_contents表中的总记录数
     *
     * @return 视频内容总记录数
     */
    long getContentCount();

    /**
     * 根据标题模糊查询视频内容
     * 从video_contents表中根据标题字段进行模糊查询
     *
     * @param title 要查询的标题关键词
     * @return 匹配标题关键词的视频内容列表
     */
    List<Content> getContentByTitle(@Param("title") String title);

    /**
     * 根据标题模糊查询视频内容（分页）
     * 从video_contents表中根据标题字段进行模糊查询，并按分页参数返回结果
     *
     * @param title  要查询的标题关键词
     * @param offset 偏移量，从0开始
     * @param size   页面大小，即每页返回多少条记录
     * @return 匹配标题关键词的视频内容列表
     */
    List<Content> getContentByTitleAndPage(@Param("title") String title, @Param("offset") int offset, @Param("size") int size);

    /**
     * 根据标题查询视频内容总数
     * 获取匹配标题关键词的视频内容总记录数
     *
     * @param title 要查询的标题关键词
     * @return 匹配标题关键词的视频内容总记录数
     */
    long getContentCountByTitle(@Param("title") String title);

    /**
     * 更新视频内容的点赞数量
     * 为指定视频ID的内容增加或减少点赞数量
     *
     * @param videoId 视频ID
     * @param count   变化的数量，正数为增加，负数为减少
     * @return 更新影响的行数
     */
    int updateLikedCount(@Param("videoId") String videoId, @Param("count") long count);

    /**
     * 更新视频内容的不喜欢数量
     * 为指定视频ID的内容增加或减少不喜欢数量
     *
     * @param videoId 视频ID
     * @param count   变化的数量，正数为增加，负数为减少
     * @return 更新影响的行数
     */
    int updateDislikedCount(@Param("videoId") String videoId, @Param("count") long count);

    /**
     * 获取热门视频列表（按点赞数排序）
     * 从video_data表中获取热门视频数据，按点赞数降序排列
     *
     * @param offset 偏移量，从0开始
     * @param size   页面大小，即每页返回多少条记录
     * @return 热门视频内容列表
     */
    List<Content> getHotVideos(@Param("offset") int offset, @Param("size") int size);

    List<Content> selectRandomVideos(@Param("count") int count, @Param("excludeIds") List<String> excludeIds);
    
    long getTotalVideoCount(@Param("excludeIds") List<String> excludeIds);
}
