package com.wei.ai.infrastructure.dao;

import com.wei.ai.infrastructure.dao.po.AiAgent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AI 智能体配置表 DAO 接口
 */
@Mapper
public interface IAiAgentDao {

    /**
     * 插入 AI 智能体配置
     * @param aiAgent AI 智能体配置对象
     * @return 影响行数
     */
    int insert(AiAgent aiAgent);

    /**
     * 根据 ID 更新 AI 智能体配置
     * @param aiAgent AI 智能体配置对象
     * @return 影响行数
     */
    int updateById(AiAgent aiAgent);

    /**
     * 根据 Agent ID 更新 AI 智能体配置
     * @param aiAgent AI 智能体配置对象
     * @return 影响行数
     */
    int updateByAgentId(AiAgent aiAgent);

    /**
     * 根据 ID 删除 AI 智能体配置
     * @param id 主键 ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据 Agent ID 删除 AI 智能体配置
     * @param agentId Agent ID
     * @return 影响行数
     */
    int deleteByAgentId(String agentId);

    /**
     * 根据 ID 查询 AI 智能体配置
     * @param id 主键 ID
     * @return AI 智能体配置对象
     */
    AiAgent queryById(Long id);

    /**
     * 根据 Agent ID 查询 AI 智能体配置
     * @param agentId Agent ID
     * @return AI 智能体配置对象
     */
    AiAgent queryByAgentId(String agentId);

    /**
     * 查询启用的 AI 智能体配置
     * @return AI 智能体配置列表
     */
    List<AiAgent> queryEnabledAgents();

    /**
     * 根据渠道类型查询 AI 智能体配置
     * @param channel 渠道类型
     * @return AI 智能体配置列表
     */
    List<AiAgent> queryByChannel(String channel);

    /**
     * 查询所有 AI 智能体配置
     * @return AI 智能体配置列表
     */
    List<AiAgent> queryAll();
}
