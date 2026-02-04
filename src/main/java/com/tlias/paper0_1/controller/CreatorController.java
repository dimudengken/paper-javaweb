package com.tlias.paper0_1.controller;

import com.tlias.paper0_1.entity.Creator;
import com.tlias.paper0_1.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 创作者控制器类
 * 提供创作者相关的REST API接口
 * 处理前端对创作者数据的增删改查请求
 */
@RestController
@RequestMapping("/creators")
public class CreatorController {

    /**
     * 创作者服务对象
     * 通过Spring的@Autowired注解自动注入CreatorService实例
     * 用于处理与创作者相关的业务逻辑
     */
    @Autowired
    private CreatorService creatorService;

    /**
     * 获取所有创作者信息
     * HTTP GET请求 /creators
     * 返回所有创作者的列表数据
     * 
     * @return 包含所有创作者信息的列表
     */
    @GetMapping
    public List<Creator> getAllCreators() {
        return creatorService.getAllCreators();
    }

    /**
     * 根据ID获取单个创作者信息
     * HTTP GET请求 /creators/{id}
     * 通过路径变量获取指定ID的创作者信息
     * 
     * @param id 要查询的创作者ID，从URL路径中获取
     * @return 对应ID的创作者对象
     */
    @GetMapping("/creators/{id}")
    public Creator getCreatorById(@PathVariable String id) {
        return creatorService.getCreatorById(id);
    }

    /**
     * 获取所有创作者信息
     * HTTP GET请求 /creators
     * 返回所有创作者的列表数据
     *
     * @return 包含所有创作者信息的列表
     */


}