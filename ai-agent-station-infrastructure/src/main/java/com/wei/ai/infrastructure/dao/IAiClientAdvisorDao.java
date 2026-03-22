package com.wei.ai.infrastructure.dao;

import com.wei.ai.infrastructure.dao.po.AiClientAdvisor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 顾问配置表 DAO 接口
 */
@Mapper
public interface IAiClientAdvisorDao {

    /**
     * 插入顾问配置
     * @param aiClientAdvisor 顾问配置对象
     * @return 影响行数
     */
    int insert(AiClientAdvisor aiClientAdvisor);

    /**
     * 根据 ID 更新顾问配置
     * @param aiClientAdvisor 顾问配置对象
     * @return 影响行数
     */
    int updateById(AiClientAdvisor aiClientAdvisor);

    /**
     * 根据 Advisor ID 更新顾问配置
     * @param aiClientAdvisor 顾问配置对象
     * @return 影响行数
     */
    int updateByAdvisorId(AiClientAdvisor aiClientAdvisor);

    /**
     * 根据 ID 删除顾问配置
     * @param id 主键 ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据 Advisor ID 删除顾问配置
     * @param advisorId Advisor ID
     * @return 影响行数
     */
    int deleteByAdvisorId(String advisorId);

    /**
     * 根据 ID 查询顾问配置
     * @param id 主键 ID
     * @return 顾问配置对象
     */
    AiClientAdvisor queryById(Long id);

    /**
     * 根据 Advisor ID 查询顾问配置
     * @param advisorId Advisor ID
     * @return 顾问配置对象
     */
    AiClientAdvisor queryByAdvisorId(String advisorId);

    /**
     * 查询启用的顾问配置（按顺序号排序）
     * @return 顾问配置列表
     */
    List<AiClientAdvisor> queryEnabledAdvisors();

    /**
     * 根据顾问类型查询顾问配置
     * @param advisorType 顾问类型
     * @return 顾问配置列表
     */
    List<AiClientAdvisor> queryByAdvisorType(String advisorType);

    /**
     * 查询所有顾问配置（按顺序号排序）
     * @return 顾问配置列表
     */
    List<AiClientAdvisor> queryAll();
}
