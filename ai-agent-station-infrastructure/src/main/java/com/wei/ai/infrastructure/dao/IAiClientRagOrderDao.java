package com.wei.ai.infrastructure.dao;

import com.wei.ai.infrastructure.dao.po.AiClientRagOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 知识库配置表 DAO 接口
 */
@Mapper
public interface IAiClientRagOrderDao {

    /**
     * 插入知识库配置
     * @param aiClientRagOrder 知识库配置对象
     * @return 影响行数
     */
    int insert(AiClientRagOrder aiClientRagOrder);

    /**
     * 根据 ID 更新知识库配置
     * @param aiClientRagOrder 知识库配置对象
     * @return 影响行数
     */
    int updateById(AiClientRagOrder aiClientRagOrder);

    /**
     * 根据 Rag ID 更新知识库配置
     * @param aiClientRagOrder 知识库配置对象
     * @return 影响行数
     */
    int updateByRagId(AiClientRagOrder aiClientRagOrder);

    /**
     * 根据 ID 删除知识库配置
     * @param id 主键 ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据 Rag ID 删除知识库配置
     * @param ragId Rag ID
     * @return 影响行数
     */
    int deleteByRagId(String ragId);

    /**
     * 根据 ID 查询知识库配置
     * @param id 主键 ID
     * @return 知识库配置对象
     */
    AiClientRagOrder queryById(Long id);

    /**
     * 根据 Rag ID 查询知识库配置
     * @param ragId Rag ID
     * @return 知识库配置对象
     */
    AiClientRagOrder queryByRagId(String ragId);

    /**
     * 查询启用的知识库配置
     * @return 知识库配置列表
     */
    List<AiClientRagOrder> queryEnabledRags();

    /**
     * 根据知识标签查询知识库配置
     * @param knowledgeTag 知识标签
     * @return 知识库配置列表
     */
    List<AiClientRagOrder> queryByKnowledgeTag(String knowledgeTag);

    /**
     * 查询所有知识库配置
     * @return 知识库配置列表
     */
    List<AiClientRagOrder> queryAll();
}
