        package com.tlias.paper0_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果封装类
 * 用于返回分页查询的结果，包含数据列表、总记录数等信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    /**
     * 当前页的数据列表
     */
    private List<T> items;
    
    /**
     * 总记录数
     */
    private Long total;
    
    /**
     * 每页大小
     */
    private Integer pageSize;
    
    /**
     * 当前页码
     */
    private Integer currentPage;
    
    /**
     * 总页数
     */
    private Integer totalPages;
    
    /**
     * 是否还有下一页
     */
    private Boolean hasNextPage;
}