package com.wei.ai.infrastructure.dao;

import com.wei.ai.infrastructure.dao.po.AiClientApi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OpenAI API 配置表 DAO 接口
 */
@Mapper
public interface IAiClientApiDao {

    /**
     * 插入 OpenAI API 配置
     * @param aiClientApi OpenAI API 配置对象
     * @return 影响行数
     */
    int insert(AiClientApi aiClientApi);

    /**
     * 根据 ID 更新 OpenAI API 配置
     * @param aiClientApi OpenAI API 配置对象
     * @return 影响行数
     */
    int updateById(AiClientApi aiClientApi);

    /**
     * 根据 API ID 更新 OpenAI API 配置
     * @param aiClientApi OpenAI API 配置对象
     * @return 影响行数
     */
    int updateByApiId(AiClientApi aiClientApi);

    /**
     * 根据 ID 删除 OpenAI API 配置
     * @param id 主键 ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据 API ID 删除 OpenAI API 配置
     * @param apiId API ID
     * @return 影响行数
     */
    int deleteByApiId(String apiId);

    /**
     * 根据 ID 查询 OpenAI API 配置
     * @param id 主键 ID
     * @return OpenAI API 配置对象
     */
    AiClientApi queryById(Long id);

    /**
     * 根据 API ID 查询 OpenAI API 配置
     * @param apiId API ID
     * @return OpenAI API 配置对象
     */
    AiClientApi queryByApiId(String apiId);

    /**
     * 查询启用的 API 配置
     * @return OpenAI API 配置列表
     */
    List<AiClientApi> queryEnabledApis();

    /**
     * 查询所有 API 配置
     * @return OpenAI API 配置列表
     */
    List<AiClientApi> queryAll();
}
