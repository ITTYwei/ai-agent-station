package com.wei.ai.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 智能体 - 客户端关联表实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiAgentFlowConfig {

    /**
     * 主键 ID
     */
    private Long id;

    /**
     * 智能体 ID
     */
    private Long agentId;

    /**
     * 客户端 ID
     */
    private Long clientId;

    /**
     * 序列号 (执行顺序)
     */
    private Integer sequence;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
