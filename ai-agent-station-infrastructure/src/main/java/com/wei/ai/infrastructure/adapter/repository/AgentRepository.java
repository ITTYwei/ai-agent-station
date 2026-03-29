package com.wei.ai.infrastructure.adapter.repository;

import com.alibaba.fastjson2.JSON;
import com.wei.ai.domain.agent.adapter.repository.IAgentRepository;
import com.wei.ai.domain.agent.model.valobj.*;
import com.wei.ai.infrastructure.dao.*;
import com.wei.ai.infrastructure.dao.po.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.wei.ai.domain.agent.model.valobj.AiAgentEnumVO.*;

@Slf4j
@Repository
public class AgentRepository implements IAgentRepository {
    private IAiAgentDao aiAgentDao;

    @Resource
    private IAiAgentFlowConfigDao aiAgentFlowConfigDao;

    @Resource
    private IAiAgentTaskScheduleDao aiAgentTaskScheduleDao;

    @Resource
    private IAiClientAdvisorDao aiClientAdvisorDao;

    @Resource
    private IAiClientApiDao aiClientApiDao;

    @Resource
    private IAiClientConfigDao aiClientConfigDao;

    @Resource
    private IAiClientDao aiClientDao;

    @Resource
    private IAiClientModelDao aiClientModelDao;

    @Resource
    private IAiClientRagOrderDao aiClientRagOrderDao;

    @Resource
    private IAiClientSystemPromptDao aiClientSystemPromptDao;

    @Resource
    private IAiClientToolMcpDao aiClientToolMcpDao;

    /**
     * 根据客户端 ID 列表查询关联的 API 信息
     * 查询链路：Client -> Model -> API
     *
     * @param clientIdList 客户端 ID 列表
     * @return API 值对象列表
     */
    @Override
    public List<AiClientApiVO> queryAiClientApiVOListByClientIds(List<String> clientIdList) {
        // 参数校验：如果客户端 ID 列表为空，直接返回空列表
        if (clientIdList == null || clientIdList.isEmpty()) {
            return List.of();
        }

        ArrayList<AiClientApiVO> result = new ArrayList<>();

        // 遍历每个客户端 ID，查询其关联的 API
        for (String clientId : clientIdList) {
            // 步骤 1: 查询客户端的配置信息 (来源：Client, 目标：Model)
            List<AiClientConfig> aiClientConfigs = aiClientConfigDao.queryBySourceTypeAndSourceId(AI_CLIENT.getCode(), clientId);

            // 如果该客户端没有配置，跳过继续处理下一个
            if (aiClientConfigs == null || aiClientConfigs.isEmpty()) {
                continue;
            }

            // 遍历客户端的所有配置
            for (AiClientConfig aiClientConfig : aiClientConfigs) {
                // 筛选条件：配置目标是模型类型且状态启用 (status=1)
                if (AI_CLIENT_MODEL.getCode().equals(aiClientConfig.getTargetType()) && aiClientConfig.getStatus() == 1) {
                    String modelId = aiClientConfig.getTargetId();

                    // 步骤 2: 根据模型 ID 查询模型详情
                    AiClientModel aiClientModel = aiClientModelDao.queryByModelId(modelId);

                    // 如果模型不存在或未启用，跳过
                    if (aiClientModel == null || aiClientModel.getStatus() != 1) {
                        continue;
                    }

                    // 步骤 3: 根据模型关联的 API ID 查询 API 详情
                    AiClientApi aiClientApi = aiClientApiDao.queryByApiId(aiClientModel.getApiId());

                    // 构建 API 值对象
                    AiClientApiVO apiVO = AiClientApiVO.builder()
                            .apiId(aiClientApi.getApiId())
                            .baseUrl(aiClientApi.getBaseUrl())
                            .apiKey(aiClientApi.getApiKey())
                            .completionsPath(aiClientApi.getCompletionsPath())
                            .embeddingsPath(aiClientApi.getEmbeddingsPath())
                            .build();

                    // 去重：如果结果集中不存在相同的 API ID，则添加
                    if (result.stream().noneMatch(vo -> vo.getApiId().equals(apiVO.getApiId()))) {
                        result.add(apiVO);
                    }
                }
            }
        }

        return result;
    }

    /**
     * 根据客户端 ID 列表查询关联的模型信息
     * 查询链路：Client -> Model
     *
     * @param clientIdList 客户端 ID 列表
     * @return 模型值对象列表
     */
    @Override
    public List<AiClientModelVO> queryAiClientModelVOByClientIds(List<String> clientIdList) {
        // 参数校验：如果客户端 ID 列表为空，直接返回空列表
        if (clientIdList == null || clientIdList.isEmpty()) {
            return List.of();
        }

        ArrayList<AiClientModelVO> result = new ArrayList<>();

        // 遍历每个客户端 ID，查询其关联的模型
        for (String clientId : clientIdList) {
            // 步骤 1: 查询客户端的配置信息 (来源：Client, 目标：Model)
            List<AiClientConfig> aiClientConfigs = aiClientConfigDao.queryBySourceTypeAndSourceId(AI_CLIENT.getCode(), clientId);

            // 如果该客户端没有配置，跳过继续处理下一个
            if (aiClientConfigs == null || aiClientConfigs.isEmpty()) {
                continue;
            }

            // 遍历客户端的所有配置
            for (AiClientConfig aiClientConfig : aiClientConfigs) {
                // 筛选条件：配置目标是模型类型且状态启用 (status=1)
                if (AI_CLIENT_MODEL.getCode().equals(aiClientConfig.getTargetType()) && aiClientConfig.getStatus() == 1) {
                    String modelId = aiClientConfig.getTargetId();

                    // 步骤 2: 根据模型 ID 查询模型详情
                    AiClientModel aiClientModel = aiClientModelDao.queryByModelId(modelId);

                    // 如果模型不存在或未启用，跳过
                    if (aiClientModel == null || aiClientModel.getStatus() != 1) {
                        continue;
                    }

                    // 构建模型值对象
                    AiClientModelVO modelVO = AiClientModelVO.builder()
                            .modelId(aiClientModel.getModelId())
                            .apiId(aiClientModel.getApiId())
                            .modelName(aiClientModel.getModelName())
                            .modelType(aiClientModel.getModelType())
                            .build();

                    // 去重：如果结果集中不存在相同的模型 ID，则添加
                    if (result.stream().noneMatch(vo -> vo.getModelId().equals(modelVO.getModelId()))) {
                        result.add(modelVO);
                    }
                }
            }
        }

        return result;
    }


    /**
     * 根据客户端 ID 列表查询关联的 MCP 工具信息
     * 查询链路：Client -> Model -> MCP Tool
     *
     * @param clientIdList 客户端 ID 列表
     * @return MCP 工具值对象列表
     */
    @Override
    public List<AiClientToolMcpVO> queryAiClientToolMcpVOByClientIds(List<String> clientIdList) {
        // 参数校验：如果客户端 ID 列表为空，直接返回空列表
        if (clientIdList == null || clientIdList.isEmpty()) {
            return List.of();
        }

        ArrayList<AiClientToolMcpVO> result = new ArrayList<>();
        HashSet<String> processedMcpIds = new HashSet<>();

        // 遍历每个客户端 ID，查询其关联的 MCP 工具
        for (String clientId : clientIdList) {
            // 步骤 1: 查询客户端的配置信息 (来源：Client, 目标：Model)
            List<AiClientConfig> aiClientConfigs = aiClientConfigDao.queryBySourceTypeAndSourceId(AI_CLIENT.getCode(), clientId);

            // 如果该客户端没有配置，跳过继续处理下一个
            if (aiClientConfigs == null || aiClientConfigs.isEmpty()) {
                continue;
            }

            // 遍历客户端的所有配置
            for (AiClientConfig aiClientConfig : aiClientConfigs) {
                // 筛选条件：配置目标是Client类型且状态启用 (status=1)
                if (AI_CLIENT_MODEL.getCode().equals(aiClientConfig.getTargetType()) && aiClientConfig.getStatus() == 1) {
                    String modelId = aiClientConfig.getTargetId();

                    // 步骤 2: 根据模型 ID 查询模型详情
                    AiClientModel aiClientModel = aiClientModelDao.queryByModelId(modelId);

                    // 如果模型不存在或未启用，跳过
                    if (aiClientModel == null || aiClientModel.getStatus() != 1) {
                        continue;
                    }

                    // 步骤 3: 查询模型的配置信息 (来源：Model, 目标：MCP Tool)
                    List<AiClientConfig> aiClientModelConfigList = aiClientConfigDao.queryBySourceTypeAndSourceId(AI_CLIENT_MODEL.getCode(), modelId);

                    // 如果模型没有配置，跳过
                    if (aiClientModelConfigList == null || aiClientModelConfigList.isEmpty()) {
                        continue;
                    }

                    // 遍历模型的所有配置
                    for (AiClientConfig clientConfig : aiClientModelConfigList) {
                        // 筛选条件：配置目标是 MCP 工具类型且状态启用 (status=1)
                        if (AI_CLIENT_TOOL_MCP.getCode().equals(clientConfig.getTargetType()) && clientConfig.getStatus() == 1) {
                            String mcpId = clientConfig.getTargetId();
                            if (processedMcpIds.contains(mcpId)) {
                                continue;
                            }
                            processedMcpIds.add(mcpId);

                            // 步骤 4: 根据 MCP ID 查询 MCP 工具详情
                            AiClientToolMcp aiClientToolMcp = aiClientToolMcpDao.queryByMcpId(mcpId);

                            // 如果 MCP 工具不存在或未启用，跳过
                            if (aiClientToolMcp == null || aiClientToolMcp.getStatus() != 1) {
                                continue;
                            }

                            // 构建 MCP 工具值对象并添加到结果集
                            AiClientToolMcpVO mcpVO = AiClientToolMcpVO.builder()
                                    .mcpId(aiClientToolMcp.getMcpId())
                                    .mcpName(aiClientToolMcp.getMcpName())
                                    .transportType(aiClientToolMcp.getTransportType())
                                    .transportConfig(aiClientToolMcp.getTransportConfig())
                                    .requestTimeout(aiClientToolMcp.getRequestTimeout())
                                    .build();
                            result.add(mcpVO);
                        }
                    }
                }
            }
        }

        return result;
    }


    /**
     * 根据客户端 ID 列表查询关联的系统提示信息
     * 链路：Client -> Model -> System Prompt
     *
     * @param clientIdList 客户端 ID 列表
     * @return 系统提示值对象列表
     */
    @Override
    public List<AiClientSystemPromptVO> queryAiClientSystemPromptVOByClientIds(List<String> clientIdList) {
        if (clientIdList == null || clientIdList.isEmpty()) {
            return List.of();
        }
        ArrayList<AiClientSystemPromptVO> result = new ArrayList<>();
        Set<String> processedPromptIds = new HashSet<>();
        for (String clientId : clientIdList) {
            // 1. 通过clientId查询关联的prompt配置
            List<AiClientConfig> aiClientConfigs = aiClientConfigDao.queryBySourceTypeAndSourceId(AI_CLIENT.getCode(), clientId);
            if (aiClientConfigs == null || aiClientConfigs.isEmpty()) {
                continue;
            }
            for (AiClientConfig aiClientConfig : aiClientConfigs) {
                if (AI_CLIENT_SYSTEM_PROMPT.getCode().equals(aiClientConfig.getTargetType()) && aiClientConfig.getStatus() == 1) {
                    String promptId = aiClientConfig.getTargetId();
                    if (processedPromptIds.contains(promptId)) {
                        continue;
                    }
                    processedPromptIds.add(promptId);
                    // 2. 通过promptId查询ai_client_system_prompt表获取系统提示词配置
                    AiClientSystemPrompt aiClientSystemPrompt = aiClientSystemPromptDao.queryByPromptId(promptId);
                    if (aiClientSystemPrompt == null) {
                        continue;
                    }
                    AiClientSystemPromptVO systemPromptVO = AiClientSystemPromptVO.builder()
                            .promptId(aiClientSystemPrompt.getPromptId())
                            .promptName(aiClientSystemPrompt.getPromptName())
                            .promptContent(aiClientSystemPrompt.getPromptContent())
                            .description(aiClientSystemPrompt.getDescription())
                            .build();
                    result.add(systemPromptVO);
                }
            }
        }
        return result;
    }



    /**
     * 根据客户端 ID 列表查询关联的顾问 (Advisor) 信息
     * 查询链路：Client -> Advisor
     *
     * @param clientIdList 客户端 ID 列表
     * @return 顾问值对象列表
     */
    @Override
    public List<AiClientAdvisorVO> queryAiClientAdvisorVOByClientIds(List<String> clientIdList) {
        // 参数校验：如果客户端 ID 列表为空，直接返回空列表
        if (clientIdList == null || clientIdList.isEmpty()) {
            return List.of();
        }

        ArrayList<AiClientAdvisorVO> result = new ArrayList<>();
        // 用于记录已处理过的顾问 ID，避免重复添加
        Set<String> processedAdvisorIds = new HashSet<>();

        // 遍历每个客户端 ID，查询其关联的顾问
        for (String clientId : clientIdList) {
            // 步骤 1: 查询客户端的配置信息 (来源：Client, 目标：可能是 Advisor)
            List<AiClientConfig> aiClientConfigs = aiClientConfigDao.queryBySourceTypeAndSourceId(AI_CLIENT.getCode(), clientId);

            // 如果该客户端没有配置，跳过继续处理下一个
            if (aiClientConfigs == null || aiClientConfigs.isEmpty()) {
                continue;
            }

            // 遍历客户端的所有配置
            for (AiClientConfig aiClientConfig : aiClientConfigs) {
                // 筛选条件：配置目标是顾问类型且状态启用 (status=1)
                if (AI_CLIENT_ADVISOR.getCode().equals(aiClientConfig.getTargetType()) && aiClientConfig.getStatus() == 1) {
                    String adviceId = aiClientConfig.getTargetId();

                    // 去重检查：如果该顾问已经处理过，跳过
                    if (processedAdvisorIds.contains(adviceId)) {
                        continue;
                    }
                    processedAdvisorIds.add(adviceId);

                    // 步骤 2: 根据顾问 ID 查询顾问详情
                    AiClientAdvisor aiClientAdvisor = aiClientAdvisorDao.queryByAdvisorId(adviceId);

                    // 如果顾问不存在，跳过
                    if (aiClientAdvisor == null) {
                        continue;
                    }

                    // 初始化顾问扩展属性 (根据顾问类型解析不同的扩展参数)
                    AiClientAdvisorVO.RagAnswer ragAnswer = null;
                    AiClientAdvisorVO.ChatMemory chatMemory = null;

                    // 步骤 3: 根据顾问类型解析扩展参数
                    if (AdvisorTypeEnumVO.RAG_ANSWER.getCode().equals(aiClientAdvisor.getAdvisorType())) {
                        // RAG 答案类型：解析 RagAnswer 对象
                        ragAnswer = JSON.parseObject(aiClientAdvisor.getExtParam(), AiClientAdvisorVO.RagAnswer.class);
                    } else if (AdvisorTypeEnumVO.CHAT_MEMORY.getCode().equals(aiClientAdvisor.getAdvisorType())) {
                        // 聊天记忆类型：解析 ChatMemory 对象
                        chatMemory = JSON.parseObject(aiClientAdvisor.getExtParam(), AiClientAdvisorVO.ChatMemory.class);
                    }

                    // 步骤 4: 构建顾问值对象并添加到结果集
                    AiClientAdvisorVO aiClientAdvisorVO = AiClientAdvisorVO.builder()
                            .advisorId(aiClientAdvisor.getAdvisorId())
                            .advisorName(aiClientAdvisor.getAdvisorName())
                            .advisorType(aiClientAdvisor.getAdvisorType())
                            .orderNum(aiClientAdvisor.getOrderNum())
                            .chatMemory(chatMemory)
                            .ragAnswer(ragAnswer)
                            .build();
                    result.add(aiClientAdvisorVO);
                }
            }
        }

        return result;
    }


    /**
     * 根据客户端 ID 列表查询客户端详情及其关联配置
     * 查询链路：Client -> (Model, MCP Tool, System Prompt, Advisor)
     *
     * @param clientIdList 客户端 ID 列表
     * @return 客户端值对象列表（包含所有关联配置）
     */
    @Override
    public List<AiClientVO> queryAiClientVOByClientIds(List<String> clientIdList) {
        // 参数校验：如果客户端 ID 列表为空，直接返回空列表
        if (clientIdList == null || clientIdList.isEmpty()) {
            return List.of();
        }

        List<AiClientVO> result = new ArrayList<>();

        // 遍历每个客户端 ID，查询其完整配置信息
        for (String clientId : clientIdList) {
            // 步骤 1: 查询客户端基础信息
            AiClient aiClient = aiClientDao.queryByClientId(clientId);

            // 如果客户端不存在或未启用 (status != 1)，跳过
            if (aiClient == null || aiClient.getStatus() != 1) {
                continue;
            }

            // 步骤 2: 查询客户端的所有配置项 (来源：Client)
            List<AiClientConfig> aiClientConfigs = aiClientConfigDao.queryBySourceTypeAndSourceId(AI_CLIENT.getCode(), clientId);

            // 如果客户端没有任何配置，跳过
            if (aiClientConfigs == null || aiClientConfigs.isEmpty()) {
                continue;
            }

            // 初始化各类配置的容器
            String modelId = null;  // 模型 ID（一个客户端只能绑定一个模型）
            ArrayList<String> promptIdList = new ArrayList<>();    // 系统提示词 ID 列表（可多个）
            ArrayList<String> toolMcpIdList = new ArrayList<>();   // MCP 工具 ID 列表（可多个）
            ArrayList<String> advisorIdList = new ArrayList<>();   // 顾问 ID 列表（可多个）

            // 步骤 3: 遍历客户端的所有配置，分类收集各类型配置 ID
            for (AiClientConfig aiClientConfig : aiClientConfigs) {
                // 只处理启用的配置
                if (aiClientConfig.getStatus() != 1) {
                    continue;
                }

                String targetId = aiClientConfig.getTargetId();      // 配置目标 ID
                String targetType = aiClientConfig.getTargetType();  // 配置目标类型

                // 步骤 4: 根据配置类型，分发到不同的集合中
                switch (AiAgentEnumVO.getByCode(targetType)){
                    case AI_CLIENT_MODEL -> modelId = targetId;           // 模型配置（单例）
                    case AI_CLIENT_TOOL_MCP -> toolMcpIdList.add(targetId);  // MCP 工具（多选）
                    case AI_CLIENT_SYSTEM_PROMPT -> promptIdList.add(targetId); // 系统提示词（多选）
                    case AI_CLIENT_ADVISOR -> advisorIdList.add(targetId);    // 顾问（多选）
                    default -> throw new RuntimeException("不支持的配置类型：" + targetType);
                }

                // 步骤 5: 构建客户端值对象并添加到结果集
                // 注意：每次循环都会创建一个新的 VO，但只有最后一次是有效的（因为 modelId 等会变化）
                AiClientVO aiClientVO = AiClientVO.builder()
                        .clientId(aiClient.getClientId())
                        .clientName(aiClient.getClientName())
                        .description(aiClient.getDescription())
                        .modelId(modelId)
                        .promptIdList(promptIdList)
                        .mcpIdList(toolMcpIdList)
                        .advisorIdList(advisorIdList)
                        .build();
                result.add(aiClientVO);
            }
        }

        return result;
    }



    @Override
    public List<AiClientApiVO> queryAiClientApiVOListByModelIds(List<String> modelIdList) {
        if (modelIdList == null || modelIdList.isEmpty()){
            return List.of();
        }
        List<AiClientApiVO> result = new ArrayList<>();

        for (String modelId : modelIdList) {
            AiClientModel aiClientModel = aiClientModelDao.queryByModelId(modelId);
            if (aiClientModel == null || aiClientModel.getStatus() != 1){
                continue;
            }
            String apiId = aiClientModel.getApiId();
            AiClientApi aiClientApi = aiClientApiDao.queryByApiId(apiId);
            if (aiClientApi == null || aiClientApi.getStatus() != 1) {
                continue;
            }
            AiClientApiVO apiVO = AiClientApiVO.builder()
                    .apiId(aiClientApi.getApiId())
                    .baseUrl(aiClientApi.getBaseUrl())
                    .apiKey(aiClientApi.getApiKey())
                    .completionsPath(aiClientApi.getCompletionsPath())
                    .embeddingsPath(aiClientApi.getEmbeddingsPath())
                    .build();
            // 避免重复添加相同的API配置
            if (result.stream().noneMatch(vo -> vo.getApiId().equals(apiVO.getApiId()))) {
                result.add(apiVO);
            }
        }
        return result;
    }

    @Override
    public List<AiClientModelVO> queryAiClientModelVOByModelIds(List<String> modelIdList) {
        if (modelIdList == null || modelIdList.isEmpty()) {
            return List.of();
        }

        List<AiClientModelVO> result = new ArrayList<>();

        for (String modelId : modelIdList) {
            // 通过modelId查询模型配置
            AiClientModel model = aiClientModelDao.queryByModelId(modelId);
            if (model != null && model.getStatus() == 1) {
                // 转换为VO对象
                AiClientModelVO modelVO = AiClientModelVO.builder()
                        .modelId(model.getModelId())
                        .apiId(model.getApiId())
                        .modelName(model.getModelName())
                        .modelType(model.getModelType())
                        .build();

                // 避免重复添加相同的模型配置
                if (result.stream().noneMatch(vo -> vo.getModelId().equals(modelVO.getModelId()))) {
                    result.add(modelVO);
                }
            }
        }

        return result;
    }
}
