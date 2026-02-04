package com.tlias.paper0_1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创作者实体类
 * 映射数据库merged_creators表的结构
 * 使用Lombok注解自动生成getter/setter等方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Creator {

    /**
     * 用户ID - 创作者的唯一标识符
     * 对应数据库表中的user_id字段
     */
    private String user_id;
    
    /**
     * 昵称 - 创作者的显示名称
     * 对应数据库表中的nickname字段
     */
    private String nickname;
    
    /**
     * 性别 - 创作者的性别信息
     * 对应数据库表中的sex字段
     */
    private String sex;
    
    /**
     * 签名 - 创作者的个人签名或简介
     * 对应数据库表中的sign字段
     */
    private String sign;
    
    /**
     * 头像 - 创作者头像图片的URL地址
     * 对应数据库表中的avatar字段
     */
    private String avatar;
    
    /**
     * 最后修改时间戳 - 记录最后更新的时间
     * 对应数据库表中的last_modify_ts字段
     */
    private String last_modify_ts;
    
    /**
     * 总粉丝数 - 创作者拥有的粉丝总数
     * 对应数据库表中的total_fans字段
     */
    private String total_fans;
    
    /**
     * 总获赞数 - 创作者作品获得的总点赞数
     * 对应数据库表中的total_liked字段
     */
    private String total_liked;
    
    /**
     * 用户等级 - 创作者的等级标识
     * 对应数据库表中的user_rank字段
     */
    private String user_rank;
    
    /**
     * 是否官方认证 - 标识创作者是否为官方认证账号
     * 对应数据库表中的is_official字段
     */
    private String is_official;
}
