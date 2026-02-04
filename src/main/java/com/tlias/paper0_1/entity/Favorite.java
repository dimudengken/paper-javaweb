package com.tlias.paper0_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 收藏夹实体类
 * 对应数据库中的favorite表，存储用户的收藏夹信息
 * 包含收藏夹名称、描述以及关联的用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    
    /**
     * 收藏夹ID - 主键，自增长
     */
    private Long id;
    
    /**
     * 用户ID - 关联user表的外键
     */
    private Long user_id;
    
    /**
     * 收藏夹名称 - 非空
     */
    private String name;
    
    /**
     * 收藏夹描述
     */
    private String description;
    
    /**
     * 创建时间 - 收藏夹的创建时间
     */
    private LocalDateTime created_at;
    
    /**
     * 更新时间 - 收藏夹信息最后一次更新的时间
     */
    private LocalDateTime updated_at;
}