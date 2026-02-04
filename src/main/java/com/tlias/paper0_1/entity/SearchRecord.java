package com.tlias.paper0_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 搜索记录实体类
 * 对应数据库中的search_record表，存储用户的搜索关键词记录
 * 用于统计热门搜索词和个性化推荐
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRecord {
    
    /**
     * 搜索记录ID - 主键，自增长
     */
    private Long id;
    
    /**
     * 搜索关键词 - 非空
     */
    private String keyword;
    
    /**
     * 搜索次数 - 默认值为1
     */
    private Integer count;
    
    /**
     * 创建时间 - 搜索记录的创建时间
     */
    private LocalDateTime created_at;
    
    /**
     * 更新时间 - 搜索记录最后一次更新的时间
     */
    private LocalDateTime updated_at;
}