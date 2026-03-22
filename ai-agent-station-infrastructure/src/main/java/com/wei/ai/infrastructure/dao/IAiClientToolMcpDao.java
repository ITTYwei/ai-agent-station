package com.wei.ai.infrastructure.dao;

import com.wei.ai.infrastructure.dao.po.AiClientToolMcp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MCP 客户端配置表 DAO 接口
 */
@Mapper
public interface IAiClientToolMcpDao {

    /**
     * 插入 MCP 客户端配置
     * @param aiClientToolMcp MCP 客户端配置对象
     * @return 影响行数
     */
    int insert(AiClientToolMcp aiClientToolMcp);

    /**
     * 根据 ID 更新 MCP 客户端配置
     * @param aiClientToolMcp MCP 客户端配置对象
     * @return 影响行数
     */
    int updateById(AiClientToolMcp aiClientToolMcp);

    /**
     * 根据 MCP ID 更新 MCP 客户端配置
     * @param aiClientToolMcp MCP 客户端配置对象
     * @return 影响行数
     */
    int updateByMcpId(AiClientToolMcp aiClientToolMcp);

    /**
     * 根据 ID 删除 MCP 客户端配置
     * @param id 主键 ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据 MCP ID 删除 MCP 客户端配置
     * @param mcpId MCP ID
     * @return 影响行数
     */
    int deleteByMcpId(String mcpId);

    /**
     * 根据 ID 查询 MCP 客户端配置
     * @param id 主键 ID
     * @return MCP 客户端配置对象
     */
    AiClientToolMcp queryById(Long id);

    /**
     * 根据 MCP ID 查询 MCP 客户端配置
     * @param mcpId MCP ID
     * @return MCP 客户端配置对象
     */
    AiClientToolMcp queryByMcpId(String mcpId);

    /**
     * 查询启用的 MCP 客户端配置
     * @return MCP 客户端配置列表
     */
    List<AiClientToolMcp> queryEnabledMcps();

    /**
     * 根据传输类型查询 MCP 客户端配置
     * @param transportType 传输类型
     * @return MCP 客户端配置列表
     */
    List<AiClientToolMcp> queryByTransportType(String transportType);

    /**
     * 查询所有 MCP 客户端配置
     * @return MCP 客户端配置列表
     */
    List<AiClientToolMcp> queryAll();
}
