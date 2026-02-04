package com.tlias.paper0_1.controller;

import com.tlias.paper0_1.entity.User_Result;
import com.tlias.paper0_1.entity.User;
import com.tlias.paper0_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户控制器类
 * 提供用户相关的REST API接口
 * 处理前端对用户数据的登录、注册、信息管理等请求
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * 用户服务对象
     * 通过Spring的@Autowired注解自动注入UserService实例
     * 用于处理与用户相关的业务逻辑
     */
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * HTTP POST请求 /api/user/account/login
     * 验证用户名和密码，返回用户信息和令牌
     * 
     * @param loginRequest 登录请求体，包含username和password
     * @return User_Result<User> 登录结果，包含用户信息和令牌
     */
    @PostMapping("/account/login")
    public User_Result<User> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    /**
     * 用户注册
     * HTTP POST请求 /api/user/account/register
     * 创建新用户账户
     * 
     * @param registerRequest 注册请求体，包含username、password和email
     * @return User_Result<User> 注册结果，包含新创建的用户信息
     */
    @PostMapping("/account/register")
    public User_Result<User> register(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest.getUsername(), 
                                   registerRequest.getPassword(), 
                                   registerRequest.getEmail());
    }

    /**
     * 用户登出
     * HTTP GET请求 /api/user/account/logout
     * 清除用户登录状态
     * 
     * @return User_Result<Void> 登出结果
     */
    @GetMapping("/account/logout")
    public User_Result<Void> logout() {
        return userService.logout();
    }

    /**
     * 获取用户信息
     * HTTP GET请求 /api/user/personal/info
     * 获取当前登录用户的基本信息
     * 
     * @return User_Result<User> 用户信息结果
     */
    @GetMapping("/personal/info")
    public User_Result<User> getCurrentUserInfo() {
        return userService.getCurrentUserInfo();
    }

    /**
     * 更新用户信息
     * HTTP POST请求 /api/user/info/update
     * 更新用户的昵称和签名等信息
     * 
     * @param updateRequest 更新请求体，包含nickname和signature
     * @return User_Result<Void> 更新结果
     */
    @PostMapping("/info/update")
    public User_Result<Void> updateUserInfo(@RequestBody UpdateInfoRequest updateRequest) {
        // 这里应该从安全上下文获取当前用户ID
        // 为了演示，暂时使用默认ID
        Long currentUserId = 1L; // 实际应用中应从安全上下文获取
        return userService.updateUserInfo(currentUserId, 
                                        updateRequest.getNickname(), 
                                        updateRequest.getSignature());
    }

    /**
     * 更新头像
     * HTTP POST请求 /api/user/avatar/update
     * 更新用户的头像URL
     * 
     * @param file 上传的头像文件
     * @return User_Result<String> 更新结果，包含新头像URL
     */
    @PostMapping("/avatar/update")
    public User_Result<String> updateAvatar(@RequestParam("file") MultipartFile file) {
        // 这里应该从安全上下文获取当前用户ID
        // 为了演示，暂时使用默认ID
        Long currentUserId = 1L; // 实际应用中应从安全上下文获取
        
        // 实际应用中需要处理文件上传逻辑
        // 这里只是模拟返回一个头像URL
        String avatarUrl = "/uploads/avatars/" + file.getOriginalFilename();
        return userService.updateUserAvatar(currentUserId, avatarUrl);
    }

    /**
     * 更新密码
     * HTTP POST请求 /api/user/password/update
     * 更新用户的密码
     * 
     * @param passwordRequest 密码更新请求体，包含oldPassword和newPassword
     * @return User_Result<Void> 更新结果
     */
    @PostMapping("/password/update")
    public User_Result<Void> updatePassword(@RequestBody PasswordUpdateRequest passwordRequest) {
        // 这里应该从安全上下文获取当前用户ID
        // 为了演示，暂时使用默认ID
        Long currentUserId = 1L; // 实际应用中应从安全上下文获取
        return userService.updatePassword(currentUserId, 
                                        passwordRequest.getOldPassword(), 
                                        passwordRequest.getNewPassword());
    }

    /**
     * 获取指定用户信息
     * HTTP GET请求 /api/user/info/get-one
     * 根据用户ID获取用户的基本信息
     * 
     * @param uid 用户ID
     * @return User_Result<User> 用户信息结果
     */
    @GetMapping("/info/get-one")
    public User_Result<User> getUserInfoById(@RequestParam("uid") Long uid) {
        return userService.getUserInfoById(uid);
    }

    // 内部请求类定义
    /**
     * 登录请求类
     * 用于接收登录请求的数据
     */
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * 注册请求类
     * 用于接收注册请求的数据
     */
    public static class RegisterRequest {
        private String username;
        private String password;
        private String email;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    /**
     * 更新信息请求类
     * 用于接收更新用户信息请求的数据
     */
    public static class UpdateInfoRequest {
        private String nickname;
        private String signature;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }

    /**
     * 密码更新请求类
     * 用于接收密码更新请求的数据
     */
    public static class PasswordUpdateRequest {
        private String oldPassword;
        private String newPassword;

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
}