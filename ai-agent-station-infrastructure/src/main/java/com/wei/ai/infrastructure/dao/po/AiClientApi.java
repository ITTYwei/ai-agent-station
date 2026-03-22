package com.wei.ai.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * OpenAI API 配置表实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiClientApi {

    /**
     * 自增主键 ID
     */
    private Long id;

    /**
     * 全局唯一配置 ID
     */
    private String apiId;

    /**
     * API 基础 URL
     */
    private String baseUrl;

    /**
     * API 密钥
     */
    private String apiKey;

    /**
     * 补全 API 路径
     */
    private String completionsPath;

    /**
     * 嵌入 API 路径
     */
    private String embeddingsPath;

    /**
     * 状态：0-禁用，1-启用
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
