package com.wei.ai.infrastructure.dao;

import com.wei.ai.infrastructure.dao.po.AiAgentTaskSchedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 智能体任务调度配置表 DAO 接口
 */
@Mapper
public interface IAiAgentTaskScheduleDao {

    /**
     * 插入智能体任务调度配置
     * @param aiAgentTaskSchedule 智能体任务调度配置对象
     * @return 影响行数
     */
    int insert(AiAgentTaskSchedule aiAgentTaskSchedule);

    /**
     * 根据 ID 更新智能体任务调度配置
     * @param aiAgentTaskSchedule 智能体任务调度配置对象
     * @return 影响行数
     */
    int updateById(AiAgentTaskSchedule aiAgentTaskSchedule);

    /**
     * 根据 ID 删除智能体任务调度配置
     * @param id 主键 ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据智能体 ID 删除任务调度配置
     * @param agentId 智能体 ID
     * @return 影响行数
     */
    int deleteByAgentId(Long agentId);

    /**
     * 根据 ID 查询智能体任务调度配置
     * @param id 主键 ID
     * @return 智能体任务调度配置对象
     */
    AiAgentTaskSchedule queryById(Long id);

    /**
     * 根据智能体 ID 查询任务调度配置列表
     * @param agentId 智能体 ID
     * @return 智能体任务调度配置列表
     */
    List<AiAgentTaskSchedule> queryByAgentId(Long agentId);

    /**
     * 查询启用的任务调度配置
     * @return 智能体任务调度配置列表
     */
    List<AiAgentTaskSchedule> queryEnabledTasks();

    /**
     * 根据状态查询任务调度配置
     * @param status 状态
     * @return 智能体任务调度配置列表
     */
    List<AiAgentTaskSchedule> queryByStatus(Integer status);

    /**
     * 查询所有任务调度配置
     * @return 智能体任务调度配置列表
     */
    List<AiAgentTaskSchedule> queryAll();
}
