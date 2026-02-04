package com.tlias.paper0_1.mapper;

import com.tlias.paper0_1.entity.Creator;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 创作者数据访问层接口
 * 定义了对创作者数据的各种数据库操作方法
 * SQL语句在对应的XML映射文件中定义
 */
@Mapper
public interface CreatorMapper {
    
    /**
     * 查询所有创作者信息
     * 从merged_creators表中获取全部创作者数据
     * 
     * @return 包含所有创作者信息的列表
     */
    List<Creator> getAllCreators();
    
    /**
     * 根据ID查询单个创作者信息
     * 从merged_creators表中根据user_id字段精确查询单条记录
     * 
     * @param id 要查询的创作者ID
     * @return 对应ID的创作者对象，如果不存在则返回null
     */
    Creator getCreatorById(String id);


    
}




