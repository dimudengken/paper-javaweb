package com.tlias.paper0_1.entity;

import javax.xml.transform.Result;

/**
 * 统一API响应结果封装类
 * 用于标准化控制器返回的数据格式
 * 包含状态码、消息和数据三个基本属性
 */
public class Creator_Result {
    
    /**
     * 状态码 - 表示请求处理结果的状态
     * 1表示成功，0表示失败
     */
    private Integer code;
    
    /**
     * 消息 - 描述请求处理结果的文本信息
     * 成功时通常为"success"，失败时为具体错误信息
     */
    private String msg;
    
    /**
     * 数据 - 包含实际的业务数据
     * 可以为任意类型的对象，通常是查询结果或处理后的数据
     */
    private Object data;

    /**
     * 创建成功的响应结果（无数据）
     * 设置状态码为1，消息为"success"，数据为空
     * 
     * @return 表示成功的Creator_Result实例
     */
    public static Creator_Result success() {
        Creator_Result result = new Creator_Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    /**
     * 创建成功的响应结果（带数据）
     * 设置状态码为1，消息为"success"，并携带指定的数据
     * 
     * @param object 要返回的业务数据
     * @return 表示成功的Creator_Result实例，包含指定数据
     */
    public static Creator_Result success(Object object) {
        Creator_Result result = new Creator_Result();
        result.code = 1;
        result.msg = "success";
        result.data = object;
        return result;
    }

    /**
     * 创建错误的响应结果
     * 设置状态码为0，并携带错误消息
     * 
     * @param msg 错误消息描述
     * @return 表示错误的Creator_Result实例
     */
    public static Creator_Result error(String msg) {
        Creator_Result result = new Creator_Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
