package com.wei.ai.infrastructure.dao;

import com.wei.ai.infrastructure.dao.po.AiClientConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AI 客户端统一关联配置表 DAO 接口
 */
@Mapper
public interface IAiClientConfigDao {

    /**
     * 插入 AI 客户端关联配置
     * @param aiClientConfig AI 客户端关联配置对象
     * @return 影响行数
     */
    int insert(AiClientConfig aiClientConfig);

    /**
     * 根据 ID 更新 AI 客户端关联配置
     * @param aiClientConfig AI 客户端关联配置对象
     * @return 影响行数
     */
    int updateById(AiClientConfig aiClientConfig);

    /**
     * 根据 ID 删除 AI 客户端关联配置
     * @param id 主键 ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据源 ID 和目标 ID 删除关联配置
     * @param sourceId 源 ID
     * @param targetId 目标 ID
     * @return 影响行数
     */
    int deleteBySourceIdAndTargetId(String sourceId, String targetId);

    /**
     * 根据 ID 查询 AI 客户端关联配置
     * @param id 主键 ID
     * @return AI 客户端关联配置对象
     */
    AiClientConfig queryById(Long id);

    /**
     * 根据源 ID 查询关联配置列表
     * @param sourceId 源 ID
     * @return AI 客户端关联配置列表
     */
    List<AiClientConfig> queryBySourceId(String sourceId);

    /**
     * 根据目标 ID 查询关联配置列表
     * @param targetId 目标 ID
     * @return AI 客户端关联配置列表
     */
    List<AiClientConfig> queryByTargetId(String targetId);

    /**
     * 根据源 ID 和目标 ID 查询关联配置
     * @param sourceId 源 ID
     * @param targetId 目标 ID
     * @return AI 客户端关联配置对象
     */
    AiClientConfig queryBySourceIdAndTargetId(String sourceId, String targetId);

    /**
     * 根据源类型和源 ID 查询关联配置列表
     * @param sourceType 源类型
     * @param sourceId 源 ID
     * @return AI 客户端关联配置列表
     */
    List<AiClientConfig> queryBySourceTypeAndSourceId(String sourceType, String sourceId);

    /**
     * 查询启用的关联配置
     * @return AI 客户端关联配置列表
     */
    List<AiClientConfig> queryEnabledConfigs();

    /**
     * 查询所有关联配置
     * @return AI 客户端关联配置列表
     */
    List<AiClientConfig> queryAll();
}
