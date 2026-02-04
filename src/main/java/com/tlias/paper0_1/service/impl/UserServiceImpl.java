package com.tlias.paper0_1.service.impl;

import com.tlias.paper0_1.entity.User_Result;
import com.tlias.paper0_1.entity.User;
import com.tlias.paper0_1.mapper.UserMapper;
import com.tlias.paper0_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户服务实现类
 * 实现UserService接口，提供用户相关的业务逻辑处理
 * 通过依赖注入使用UserMapper进行数据访问操作
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户数据访问对象
     * 通过Spring的@Autowired注解自动注入UserMapper实例
     * 用于执行与用户数据相关的数据库操作
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * 验证用户名和密码，返回用户信息和令牌
     * 
     * @param username 用户名
     * @param password 密码
     * @return User_Result<User> 登录结果，包含用户信息和令牌
     */
    @Override
    public User_Result<User> login(String username, String password) {
        try {
            // 验证用户名和密码（实际应用中应使用加密后的密码）
            User user = userMapper.findByUsername(username);
            
            if (user == null) {
                return User_Result.error("用户不存在");
            }
            
            // 这里应该比较加密后的密码，为了演示暂时直接比较
            if (!user.getPassword().equals(password)) {
                return User_Result.error("原密码错误");
            }
            
            // 设置临时令牌（实际应用中应生成JWT令牌）
            user.setPassword(null); // 不返回密码
            
            return User_Result.success("登录成功", user);
        } catch (Exception e) {
            e.printStackTrace();
            return User_Result.error("登录失败：" + e.getMessage());
        }
    }

    /**
     * 用户注册
     * 创建新用户账户
     * 
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱
     * @return User_Result<User> 注册结果，包含新创建的用户信息
     */
    @Override
    public User_Result<User> register(String username, String password, String email) {
        try {
            // 检查用户名是否已存在
            User existingUser = userMapper.findByUsername(username);
            if (existingUser != null) {
                return User_Result.error("用户名已存在");
            }
            
            // 检查邮箱是否已存在
            User existingEmail = userMapper.findByEmail(email);
            if (existingEmail != null) {
                return User_Result.error("邮箱已被注册");
            }
            
            // 创建新用户
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password); // 实际应用中应加密密码
            newUser.setEmail(email);
            newUser.setRole(0); // 默认为普通用户
            newUser.setCreated_at(LocalDateTime.now());
            newUser.setUpdated_at(LocalDateTime.now());
            
            // 保存用户
            userMapper.insert(newUser);
            
            // 从数据库获取完整用户信息（包括生成的ID）
            User savedUser = userMapper.findByUsername(username);
            savedUser.setPassword(null); // 不返回密码
            
            return User_Result.success("注册成功", savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return User_Result.error("注册失败：" + e.getMessage());
        }
    }

    /**
     * 用户登出
     * 清除用户登录状态
     * 
     * @return User_Result<Void> 登出结果
     */
    @Override
    public User_Result<Void> logout() {
        // 实际应用中可能需要清除会话或JWT令牌
        return User_Result.success("登出成功");
    }

    /**
     * 获取当前用户信息
     * 获取当前登录用户的基本信息
     * 
     * @return User_Result<User> 用户信息结果
     */
    @Override
    public User_Result<User> getCurrentUserInfo() {
        // 这里应该从安全上下文获取当前用户ID
        // 由于没有安全框架，这里返回模拟数据
        // 实际应用中需要根据当前登录用户ID查询
        return User_Result.error("功能待实现：需要认证框架支持");
    }

    /**
     * 更新用户个人信息
     * 更新用户的昵称和签名等信息
     * 
     * @param userId 用户ID
     * @param nickname 昵称
     * @param signature 个性签名
     * @return User_Result<Void> 更新结果
     */
    @Override
    public User_Result<Void> updateUserInfo(Long userId, String nickname, String signature) {
        try {
            // 检查用户是否存在
            User existingUser = userMapper.findById(userId);
            if (existingUser == null) {
                return User_Result.error("用户不存在");
            }
            
            // 更新用户信息
            userMapper.updateUserInfo(userId, nickname, signature, LocalDateTime.now());
            
            return User_Result.success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return User_Result.error("更新失败：" + e.getMessage());
        }
    }

    /**
     * 更新用户头像
     * 更新用户的头像URL
     * 
     * @param userId 用户ID
     * @param avatar 新头像URL
     * @return User_Result<String> 更新结果，包含新头像URL
     */
    @Override
    public User_Result<String> updateUserAvatar(Long userId, String avatar) {
        try {
            // 检查用户是否存在
            User existingUser = userMapper.findById(userId);
            if (existingUser == null) {
                return User_Result.error("用户不存在");
            }
            
            // 更新头像
            userMapper.updateAvatar(userId, avatar, LocalDateTime.now());
            
            return User_Result.success("更新成功", avatar);
        } catch (Exception e) {
            e.printStackTrace();
            return User_Result.error("更新失败：" + e.getMessage());
        }
    }

    /**
     * 更新用户密码
     * 更新用户的密码
     * 
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return User_Result<Void> 更新结果
     */
    @Override
    public User_Result<Void> updatePassword(Long userId, String oldPassword, String newPassword) {
        try {
            // 检查用户是否存在
            User existingUser = userMapper.findById(userId);
            if (existingUser == null) {
                return User_Result.error("用户不存在");
            }
            
            // 验证旧密码
            if (!existingUser.getPassword().equals(oldPassword)) {
                return User_Result.error("原密码错误");
            }
            
            // 更新密码
            userMapper.updatePassword(userId, newPassword, LocalDateTime.now());
            
            return User_Result.success("密码更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return User_Result.error("密码更新失败：" + e.getMessage());
        }
    }

    /**
     * 获取指定用户信息
     * 根据用户ID获取用户的基本信息
     * 
     * @param userId 用户ID
     * @return Result<User> 用户信息结果
     */
    @Override
    public User_Result<User> getUserInfoById(Long userId) {
        try {
            User user = userMapper.findById(userId);
            if (user == null) {
                return User_Result.error("用户不存在");
            }
            
            // 不返回密码
            user.setPassword(null);
            
            return User_Result.success("获取成功", user);
        } catch (Exception e) {
            e.printStackTrace();
            return User_Result.error("获取失败：" + e.getMessage());
        }
    }
}