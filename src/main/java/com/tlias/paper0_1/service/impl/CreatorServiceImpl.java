package com.tlias.paper0_1.service.impl;

import com.tlias.paper0_1.entity.Creator;
import com.tlias.paper0_1.mapper.CreatorMapper;
import com.tlias.paper0_1.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创作者服务实现类
 * 实现CreatorService接口，提供创作者相关的业务逻辑处理
 * 通过依赖注入使用CreatorMapper进行数据访问操作
 */
@Service("creatorServiceImpl")
public class CreatorServiceImpl implements CreatorService {

    /**
     * 创作者数据访问对象
     * 通过Spring的@Autowired注解自动注入CreatorMapper实例
     * 用于执行与创作者数据相关的数据库操作
     */
    @Autowired
    private CreatorMapper creatorMapper;

    /**
     * 获取所有创作者信息
     * 调用数据访问层方法获取全部创作者数据
     * 
     * @return 包含所有创作者信息的列表
     */
    @Override
    public List<Creator> getAllCreators() {
        return creatorMapper.getAllCreators();
    }

    /**
     * 根据ID获取单个创作者信息
     * 通过数据访问层根据ID查询特定的创作者信息
     * 
     * @param id 要查询的创作者ID
     * @return 对应ID的创作者对象，如果不存在则返回null
     */
    @Override
    public Creator getCreatorById(String id) {
        return creatorMapper.getCreatorById(id);
    }



}