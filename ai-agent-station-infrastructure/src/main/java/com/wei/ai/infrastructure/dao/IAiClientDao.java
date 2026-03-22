package com.wei.ai.infrastructure.dao;

import com.wei.ai.infrastructure.dao.po.AiClient;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AI 客户端配置表 DAO 接口
 */
@Mapper
public interface IAiClientDao {

    /**
     * 插入 AI 客户端配置
     * @param aiClient AI 客户端配置对象
     * @return 影响行数
     */
    int insert(AiClient aiClient);

    /**
     * 根据 ID 更新 AI 客户端配置
     * @param aiClient AI 客户端配置对象
     * @return 影响行数
     */
    int updateById(AiClient aiClient);

    /**
     * 根据 Client ID 更新 AI 客户端配置
     * @param aiClient AI 客户端配置对象
     * @return 影响行数
     */
    int updateByClientId(AiClient aiClient);

    /**
     * 根据 ID 删除 AI 客户端配置
     * @param id 主键 ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据 Client ID 删除 AI 客户端配置
     * @param clientId Client ID
     * @return 影响行数
     */
    int deleteByClientId(String clientId);

    /**
     * 根据 ID 查询 AI 客户端配置
     * @param id 主键 ID
     * @return AI 客户端配置对象
     */
    AiClient queryById(Long id);

    /**
     * 根据 Client ID 查询 AI 客户端配置
     * @param clientId Client ID
     * @return AI 客户端配置对象
     */
    AiClient queryByClientId(String clientId);

    /**
     * 查询启用的 AI 客户端配置
     * @return AI 客户端配置列表
     */
    List<AiClient> queryEnabledClients();

    /**
     * 查询所有 AI 客户端配置
     * @return AI 客户端配置列表
     */
    List<AiClient> queryAll();
}
