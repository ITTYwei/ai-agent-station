package com.wei.ai.infrastructure.dao;

import com.wei.ai.infrastructure.dao.po.AiAgentFlowConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 智能体 - 客户端关联表 DAO 接口
 */
@Mapper
public interface IAiAgentFlowConfigDao {

    /**
     * 插入智能体 - 客户端关联配置
     * @param aiAgentFlowConfig 智能体 - 客户端关联配置对象
     * @return 影响行数
     */
    int insert(AiAgentFlowConfig aiAgentFlowConfig);

    /**
     * 根据 ID 更新智能体 - 客户端关联配置
     * @param aiAgentFlowConfig 智能体 - 客户端关联配置对象
     * @return 影响行数
     */
    int updateById(AiAgentFlowConfig aiAgentFlowConfig);

    /**
     * 根据 ID 删除智能体 - 客户端关联配置
     * @param id 主键 ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据智能体 ID 和客户端 ID 删除关联配置
     * @param agentId 智能体 ID
     * @param clientId 客户端 ID
     * @return 影响行数
     */
    int deleteByAgentIdAndClientId(Long agentId, Long clientId);

    /**
     * 根据 ID 查询智能体 - 客户端关联配置
     * @param id 主键 ID
     * @return 智能体 - 客户端关联配置对象
     */
    AiAgentFlowConfig queryById(Long id);

    /**
     * 根据智能体 ID 查询关联配置列表
     * @param agentId 智能体 ID
     * @return 智能体 - 客户端关联配置列表
     */
    List<AiAgentFlowConfig> queryByAgentId(Long agentId);

    /**
     * 根据客户端 ID 查询关联配置列表
     * @param clientId 客户端 ID
     * @return 智能体 - 客户端关联配置列表
     */
    List<AiAgentFlowConfig> queryByClientId(Long clientId);

    /**
     * 根据智能体 ID 和序列号查询
     * @param agentId 智能体 ID
     * @param sequence 序列号
     * @return 智能体 - 客户端关联配置对象
     */
    AiAgentFlowConfig queryByAgentIdAndSequence(Long agentId, Integer sequence);

    /**
     * 查询所有关联配置
     * @return 智能体 - 客户端关联配置列表
     */
    List<AiAgentFlowConfig> queryAll();
}
