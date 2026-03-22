package com.wei.ai.infrastructure.dao;

import com.wei.ai.infrastructure.dao.po.AiClientModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 聊天模型配置表 DAO 接口
 */
@Mapper
public interface IAiClientModelDao {

    /**
     * 插入聊天模型配置
     * @param aiClientModel 聊天模型配置对象
     * @return 影响行数
     */
    int insert(AiClientModel aiClientModel);

    /**
     * 根据 ID 更新聊天模型配置
     * @param aiClientModel 聊天模型配置对象
     * @return 影响行数
     */
    int updateById(AiClientModel aiClientModel);

    /**
     * 根据 Model ID 更新聊天模型配置
     * @param aiClientModel 聊天模型配置对象
     * @return 影响行数
     */
    int updateByModelId(AiClientModel aiClientModel);

    /**
     * 根据 ID 删除聊天模型配置
     * @param id 主键 ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据 Model ID 删除聊天模型配置
     * @param modelId Model ID
     * @return 影响行数
     */
    int deleteByModelId(String modelId);

    /**
     * 根据 ID 查询聊天模型配置
     * @param id 主键 ID
     * @return 聊天模型配置对象
     */
    AiClientModel queryById(Long id);

    /**
     * 根据 Model ID 查询聊天模型配置
     * @param modelId Model ID
     * @return 聊天模型配置对象
     */
    AiClientModel queryByModelId(String modelId);

    /**
     * 查询启用的聊天模型配置
     * @return 聊天模型配置列表
     */
    List<AiClientModel> queryEnabledModels();

    /**
     * 根据 API ID 查询聊天模型配置列表
     * @param apiId API ID
     * @return 聊天模型配置列表
     */
    List<AiClientModel> queryByApiId(String apiId);

    /**
     * 根据模型类型查询聊天模型配置
     * @param modelType 模型类型
     * @return 聊天模型配置列表
     */
    List<AiClientModel> queryByModelType(String modelType);

    /**
     * 查询所有聊天模型配置
     * @return 聊天模型配置列表
     */
    List<AiClientModel> queryAll();
}
