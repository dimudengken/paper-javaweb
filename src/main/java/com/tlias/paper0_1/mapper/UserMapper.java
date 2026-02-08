package com.tlias.paper0_1.mapper;

import com.tlias.paper0_1.entity.User;
import com.tlias.paper0_1.entity.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.time.LocalDateTime;

/**
 * 用户数据访问层接口
 * 定义了对用户数据的各种数据库操作方法
 * SQL语句在对应的XML映射文件中定义
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户
     * 从user表中根据username字段精确查询单条记录
     * 
     * @param username 要查询的用户名
     * @return 对应用户名的用户对象，如果不存在则返回null
     */
    User findByUsername(@Param("username") String username);

    /**
     * 根据邮箱查询用户
     * 从user表中根据email字段精确查询单条记录
     * 
     * @param email 要查询的邮箱
     * @return 对应邮箱的用户对象，如果不存在则返回null
     */
    User findByEmail(@Param("email") String email);

    /**
     * 根据ID查询用户
     * 从user表中根据id字段精确查询单条记录
     * 
     * @param id 要查询的用户ID
     * @return 对应ID的用户对象，如果不存在则返回null
     */
    User findById(@Param("id") Long id);

    /**
     * 插入新用户
     * 向user表中插入一条新的用户记录
     * 
     * @param user 要插入的用户对象
     */
    void insert(User user);

    /**
     * 更新用户个人信息
     * 更新用户的昵称和签名信息
     * 
     * @param id 用户ID
     * @param nickname 新昵称
     * @param signature 新签名
     * @param updatedAt 更新时间
     */
    void updateUserInfo(@Param("id") Long id, 
                        @Param("nickname") String nickname, 
                        @Param("signature") String signature, 
                        @Param("updatedAt") LocalDateTime updatedAt);

    /**
     * 更新用户头像
     * 更新用户的头像URL
     * 
     * @param id 用户ID
     * @param avatar 新头像URL
     * @param updatedAt 更新时间
     */
    void updateAvatar(@Param("id") Long id, 
                      @Param("avatar") String avatar, 
                      @Param("updatedAt") LocalDateTime updatedAt);

    /**
     * 更新用户密码
     * 更新用户的密码
     * 
     * @param id 用户ID
     * @param password 新密码
     * @param updatedAt 更新时间
     */
    void updatePassword(@Param("id") Long id, 
                        @Param("password") String password, 
                        @Param("updatedAt") LocalDateTime updatedAt);
    
    /**
     * 获取用户收藏的视频列表
     * 
     * @param userId 用户ID
     * @return 用户收藏的视频列表
     */
    List<Content> getFavoriteVideosByUserId(@Param("userId") Long userId);
    
    /**
     * 获取用户上传的视频列表
     * 
     * @param userId 用户ID
     * @return 用户上传的视频列表
     */
    List<Content> getUploadedVideosByUserId(@Param("userId") Long userId);
    
    /**
     * 获取推荐给用户的视频列表
     * 
     * @param userId 用户ID
     * @return 推荐的视频列表
     */
    List<Content> getRecommendedVideosByUserId(@Param("userId") Long userId);
}