package com.wei.ai.infrastructure.adapter.repository;

import com.wei.ai.domain.agent.adapter.repository.IAgentRepository;
import com.wei.ai.domain.agent.model.valobj.*;
import com.wei.ai.infrastructure.dao.*;
import com.wei.ai.infrastructure.dao.po.AiClientApi;
import com.wei.ai.infrastructure.dao.po.AiClientConfig;
import com.wei.ai.infrastructure.dao.po.AiClientModel;
import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static com.wei.ai.domain.agent.model.valobj.AiAgentEnumVO.AI_CLIENT;
import static com.wei.ai.domain.agent.model.valobj.AiAgentEnumVO.AI_CLIENT_MODEL;

public class AgentRepository  implements IAgentRepository {
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

    @Override
    public List<AiClientApiVO> queryAiClientApiVOListByClientIds(List<String> clientIdList) {
        if (clientIdList == null || clientIdList.isEmpty()){
            return List.of();
        }
        ArrayList<AiClientApiVO> result = new ArrayList<>();
        for (String clientId : clientIdList) {
            List<AiClientConfig> aiClientConfigs = aiClientConfigDao.queryBySourceTypeAndSourceId(AI_CLIENT.getCode(), clientId);
            if (aiClientConfigs == null || aiClientConfigs.isEmpty()){
                continue;
            }
            for (AiClientConfig aiClientConfig : aiClientConfigs) {
                if (AI_CLIENT_MODEL.getCode().equals(aiClientConfig.getTargetType()) && aiClientConfig.getStatus() == 1){
                    String targetId = aiClientConfig.getTargetId();
                    AiClientModel aiClientModel = aiClientModelDao.queryByModelId(targetId);
                    if (aiClientModel == null || aiClientModel.getStatus() != 1){
                        continue;
                    }
                    AiClientApi aiClientApi = aiClientApiDao.queryByApiId(aiClientModel.getApiId());
                    AiClientApiVO apiVO = AiClientApiVO.builder()
                            .apiId(aiClientApi.getApiId())
                            .baseUrl(aiClientApi.getBaseUrl())
                            .apiKey(aiClientApi.getApiKey())
                            .completionsPath(aiClientApi.getCompletionsPath())
                            .embeddingsPath(aiClientApi.getEmbeddingsPath())
                            .build();
                    result.add(apiVO);
                }
            }
        }
        return result;
    }

    @Override
    public List<AiClientModelVO> AiClientModelVOByClientIds(List<String> clientIdList) {
        return List.of();
    }

    @Override
    public List<AiClientToolMcpVO> AiClientToolMcpVOByClientIds(List<String> clientIdList) {
        return List.of();
    }

    @Override
    public List<AiClientSystemPromptVO> AiClientSystemPromptVOByClientIds(List<String> clientIdList) {
        return List.of();
    }

    @Override
    public List<AiClientAdvisorVO> AiClientAdvisorVOByClientIds(List<String> clientIdList) {
        return List.of();
    }

    @Override
    public List<AiClientVO> AiClientVOByClientIds(List<String> clientIdList) {
        return List.of();
    }

    @Override
    public List<AiClientApiVO> queryAiClientApiVOListByModelIds(List<String> modelIdList) {
        return List.of();
    }

    @Override
    public List<AiClientModelVO> AiClientModelVOByModelIds(List<String> modelIdList) {
        return List.of();
    }
}
