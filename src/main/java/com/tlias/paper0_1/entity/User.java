package com.tlias.paper0_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户实体类
 * 对应数据库中的user表，存储用户的基本信息
 * 包含用户名、密码、邮箱、角色等关键用户属性
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    /**
     * 用户ID - 主键，自增长
     */
    private Long id;
    
    /**
     * 用户名 - 唯一且非空
     */
    private String username;
    
    /**
     * 密码 - MD5加密后的密码
     */
    private String password;
    
    /**
     * 邮箱 - 唯一且非空
     */
    private String email;
    
    /**
     * 头像URL - 用户头像的链接地址
     */
    private String avatar;
    
    /**
     * 昵称 - 用户的显示昵称
     */
    private String nickname;
    
    /**
     * 个性签名 - 用户的个人签名或简介
     */
    private String signature;
    
    /**
     * 角色 - 用户的角色权限（0：普通用户，1：管理员，2：超级管理员）
     */
    private Integer role;
    
    /**
     * 创建时间 - 用户账户的创建时间
     */
    private LocalDateTime created_at;
    
    /**
     * 更新时间 - 用户信息最后一次更新的时间
     */
    private LocalDateTime updated_at;


}