package com.tlias.paper0_1.service;

import com.tlias.paper0_1.entity.Creator;

import java.util.List;

public interface CreatorService {
    // 查询所有创作者
    List<Creator> getAllCreators();
    
    // 根据ID查询创作者
    Creator getCreatorById(String id);


}