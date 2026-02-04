package com.tlias.paper0_1.service;

import com.tlias.paper0_1.entity.User_Result;
import com.tlias.paper0_1.entity.User;

/**
 * 用户服务接口
 * 定义用户相关的业务逻辑方法
 * 包括用户登录、注册、信息管理等功能
 */
public interface UserService {

    /**
     * 用户登录
     * 验证用户名和密码，返回用户信息和令牌
     * 
     * @param username 用户名
     * @param password 密码
     * @return User_Result<User> 登录结果，包含用户信息和令牌
     */
    User_Result<User> login(String username, String password);

    /**
     * 用户注册
     * 创建新用户账户
     * 
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱
     * @return User_Result<User> 注册结果，包含新创建的用户信息
     */
    User_Result<User> register(String username, String password, String email);

    /**
     * 用户登出
     * 清除用户登录状态
     * 
     * @return User_Result<Void> 登出结果
     */
    User_Result<Void> logout();

    /**
     * 获取当前用户信息
     * 获取当前登录用户的基本信息
     * 
     * @return User_Result<User> 用户信息结果
     */
    User_Result<User> getCurrentUserInfo();

    /**
     * 更新用户个人信息
     * 更新用户的昵称和签名等信息
     * 
     * @param userId 用户ID
     * @param nickname 昵称
     * @param signature 个性签名
     * @return Result<Void> 更新结果
     */
    User_Result<Void> updateUserInfo(Long userId, String nickname, String signature);

    /**
     * 更新用户头像
     * 更新用户的头像URL
     * 
     * @param userId 用户ID
     * @param avatar 新头像URL
     * @return User_Result<String> 更新结果，包含新头像URL
     */
    User_Result<String> updateUserAvatar(Long userId, String avatar);

    /**
     * 更新用户密码
     * 更新用户的密码
     * 
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return Result<Void> 更新结果
     */
    User_Result<Void> updatePassword(Long userId, String oldPassword, String newPassword);

    /**
     * 获取指定用户信息
     * 根据用户ID获取用户的基本信息
     * 
     * @param userId 用户ID
     * @return User_Result<User> 用户信息结果
     */
    User_Result<User> getUserInfoById(Long userId);
}