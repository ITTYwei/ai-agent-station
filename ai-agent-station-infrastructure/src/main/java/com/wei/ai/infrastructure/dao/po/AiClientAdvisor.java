package com.wei.ai.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 顾问配置表实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiClientAdvisor {

    /**
     * 主键 ID
     */
    private Long id;

    /**
     * 顾问 ID
     */
    private String advisorId;

    /**
     * 顾问名称
     */
    private String advisorName;

    /**
     * 顾问类型 (PromptChatMemory/RagAnswer/SimpleLoggerAdvisor等)
     */
    private String advisorType;

    /**
     * 顺序号
     */
    private Integer orderNum;

    /**
     * 扩展参数配置，json 记录
     */
    private String extParam;

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
