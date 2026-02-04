package com.tlias.paper0_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应结果类
 * 用于封装API接口的统一响应格式
 * 包含状态码、消息和数据三个基本组成部分
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User_Result<T> {
    
    /**
     * 状态码 - 200表示成功，其他值表示失败
     */
    private Integer code;
    
    /**
     * 响应消息 - 描述操作结果的文本信息
     */
    private String message;
    
    /**
     * 响应数据 - 泛型类型，用于封装具体的业务数据
     */
    private T data;
    
    /**
     * 成功响应的静态工厂方法 - 无数据返回
     * 
     * @param message 成功消息
     * @return 成功的User_Result对象
     */
    public static <T> User_Result<T> success(String message) {
        User_Result<T> result = new User_Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
    
    /**
     * 成功响应的静态工厂方法 - 有数据返回
     * 
     * @param message 成功消息
     * @param data 返回的业务数据
     * @return 成功的User_Result对象
     */
    public static <T> User_Result<T> success(String message, T data) {
        User_Result<T> result = new User_Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    
    /**
     * 失败响应的静态工厂方法
     * 
     * @param message 错误消息
     * @return 失败的User_Result对象
     */
    public static <T> User_Result<T> error(String message) {
        User_Result<T> result = new User_Result<>();
        result.setCode(-1); // 使用-1表示错误
        result.setMessage(message);
        result.setData(null);
        return result;
    }
    
    /**
     * 自定义状态码的响应方法
     * 
     * @param code 状态码
     * @param message 消息
     * @param data 数据
     * @return User_Result对象
     */
    public static <T> User_Result<T> build(Integer code, String message, T data) {
        User_Result<T> result = new User_Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}