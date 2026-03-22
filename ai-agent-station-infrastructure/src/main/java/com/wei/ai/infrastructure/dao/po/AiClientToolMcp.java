package com.wei.ai.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * MCP 客户端配置表实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiClientToolMcp {

    /**
     * 主键 ID
     */
    private Long id;

    /**
     * MCP ID
     */
    private String mcpId;

    /**
     * MCP 名称
     */
    private String mcpName;

    /**
     * 传输类型 (sse/stdio)
     */
    private String transportType;

    /**
     * 传输配置 (sse/stdio)
     */
    private String transportConfig;

    /**
     * 请求超时时间 (分钟)
     */
    private Integer requestTimeout;

    /**
     * 状态 (0:禁用，1:启用)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
