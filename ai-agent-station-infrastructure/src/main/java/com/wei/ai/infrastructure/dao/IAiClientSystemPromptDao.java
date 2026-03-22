package com.wei.ai.infrastructure.dao;

import com.wei.ai.infrastructure.dao.po.AiClientSystemPrompt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统提示词配置表 DAO 接口
 */
@Mapper
public interface IAiClientSystemPromptDao {

    /**
     * 插入系统提示词配置
     * @param aiClientSystemPrompt 系统提示词配置对象
     * @return 影响行数
     */
    int insert(AiClientSystemPrompt aiClientSystemPrompt);

    /**
     * 根据 ID 更新系统提示词配置
     * @param aiClientSystemPrompt 系统提示词配置对象
     * @return 影响行数
     */
    int updateById(AiClientSystemPrompt aiClientSystemPrompt);

    /**
     * 根据 Prompt ID 更新系统提示词配置
     * @param aiClientSystemPrompt 系统提示词配置对象
     * @return 影响行数
     */
    int updateByPromptId(AiClientSystemPrompt aiClientSystemPrompt);

    /**
     * 根据 ID 删除系统提示词配置
     * @param id 主键 ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据 Prompt ID 删除系统提示词配置
     * @param promptId Prompt ID
     * @return 影响行数
     */
    int deleteByPromptId(String promptId);

    /**
     * 根据 ID 查询系统提示词配置
     * @param id 主键 ID
     * @return 系统提示词配置对象
     */
    AiClientSystemPrompt queryById(Long id);

    /**
     * 根据 Prompt ID 查询系统提示词配置
     * @param promptId Prompt ID
     * @return 系统提示词配置对象
     */
    AiClientSystemPrompt queryByPromptId(String promptId);

    /**
     * 查询启用的系统提示词配置
     * @return 系统提示词配置列表
     */
    List<AiClientSystemPrompt> queryEnabledPrompts();

    /**
     * 查询所有系统提示词配置
     * @return 系统提示词配置列表
     */
    List<AiClientSystemPrompt> queryAll();
}
