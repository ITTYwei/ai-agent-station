package com.wei.ai.test;

import com.wei.ai.infrastructure.dao.*;
import com.wei.ai.infrastructure.dao.po.AiAgent;
import com.wei.ai.infrastructure.dao.po.AiClientApi;
import com.wei.ai.infrastructure.dao.po.AiClientModel;
import com.wei.ai.infrastructure.dao.po.AiClientToolMcp;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

    @Resource
    private IAiAgentDao aiAgentDao;

    @Resource
    private IAiClientDao aiClientDao;

    @Resource
    private IAiClientToolMcpDao aiClientToolMcpDao;

    @Resource
    private IAiClientApiDao aiClientApiDao;

    @Resource
    private IAiClientModelDao aiClientModelDao;

    @Test
    public void testAiAgentDao() {
        log.info("============ 测试 AI Agent DAO ============");
        
        // 1. 插入测试数据
        AiAgent aiAgent = AiAgent.builder()
                .agentId("test_agent_001")
                .agentName("测试智能体")
                .description("这是一个测试智能体")
                .channel("agent")
                .status(1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        
        int insertCount = aiAgentDao.insert(aiAgent);
        log.info("插入结果：{}", insertCount);
        log.info("生成的 ID: {}", aiAgent.getId());

        // 2. 根据 ID 查询
        AiAgent queryResult = aiAgentDao.queryById(aiAgent.getId());
        log.info("根据 ID 查询结果：{}", queryResult);

        // 3. 根据 AgentID 查询
        AiAgent queryByAgentId = aiAgentDao.queryByAgentId("test_agent_001");
        log.info("根据 AgentID 查询结果：{}", queryByAgentId);

        // 4. 更新测试
        queryResult.setAgentName("更新后的测试智能体");
        queryResult.setUpdateTime(LocalDateTime.now());
        int updateCount = aiAgentDao.updateById(queryResult);
        log.info("更新结果：{}", updateCount);

        // 5. 查询所有
        List<AiAgent> allAgents = aiAgentDao.queryAll();
        log.info("查询所有记录数：{}", allAgents.size());

        // 6. 查询启用的
        List<AiAgent> enabledAgents = aiAgentDao.queryEnabledAgents();
        log.info("查询启用的记录数：{}", enabledAgents.size());

        // 7. 删除测试
        int deleteCount = aiAgentDao.deleteById(aiAgent.getId());
        log.info("删除结果：{}", deleteCount);

        // 验证删除
        AiAgent afterDelete = aiAgentDao.queryById(aiAgent.getId());
        log.info("删除后查询结果：{}", afterDelete);
    }

    @Test
    public void testAiClientToolMcpDao() {
        log.info("============ 测试 MCP Client DAO ============");
        
        // 1. 插入测试数据
        AiClientToolMcp mcp = AiClientToolMcp.builder()
                .mcpId("test_mcp_001")
                .mcpName("测试 MCP 服务")
                .transportType("sse")
                .transportConfig("{\"baseUri\":\"http://localhost:8080\",\"sseEndpoint\":\"/sse\"}")
                .requestTimeout(180)
                .status(1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        
        int insertCount = aiClientToolMcpDao.insert(mcp);
        log.info("插入结果：{}", insertCount);
        log.info("生成的 ID: {}", mcp.getId());

        // 2. 根据 ID 查询
        AiClientToolMcp queryResult = aiClientToolMcpDao.queryById(mcp.getId());
        log.info("根据 ID 查询结果：{}", queryResult);

        // 3. 根据 McpID 查询
        AiClientToolMcp queryByMcpId = aiClientToolMcpDao.queryByMcpId("test_mcp_001");
        log.info("根据 McpID 查询结果：{}", queryByMcpId);

        // 4. 更新测试
        queryResult.setMcpName("更新后的 MCP 服务");
        queryResult.setUpdateTime(LocalDateTime.now());
        int updateCount = aiClientToolMcpDao.updateById(queryResult);
        log.info("更新结果：{}", updateCount);

        // 5. 查询所有
        List<AiClientToolMcp> allMcps = aiClientToolMcpDao.queryAll();
        log.info("查询所有记录数：{}", allMcps.size());

        // 6. 根据传输类型查询
        List<AiClientToolMcp> sseMcps = aiClientToolMcpDao.queryByTransportType("sse");
        log.info("SSE 传输类型记录数：{}", sseMcps.size());

        // 7. 删除测试
        int deleteCount = aiClientToolMcpDao.deleteByMcpId("test_mcp_001");
        log.info("删除结果：{}", deleteCount);
    }

    @Test
    public void testAiClientApiDao() {
        log.info("============ 测试 API Config DAO ============");
        
        // 1. 插入测试数据
        AiClientApi api = AiClientApi.builder()
                .apiId("test_api_001")
                .baseUrl("https://api.test.com")
                .apiKey("test_api_key_123456")
                .completionsPath("v1/chat/completions")
                .embeddingsPath("v1/embeddings")
                .status(1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        
        int insertCount = aiClientApiDao.insert(api);
        log.info("插入结果：{}", insertCount);

        // 2. 根据 ID 查询
        AiClientApi queryResult = aiClientApiDao.queryById(api.getId());
        log.info("根据 ID 查询结果：{}", queryResult);

        // 3. 根据 ApiID 查询
        AiClientApi queryByApiId = aiClientApiDao.queryByApiId("test_api_001");
        log.info("根据 ApiID 查询结果：{}", queryByApiId);

        // 4. 更新测试
        queryResult.setBaseUrl("https://api.new.com");
        queryResult.setUpdateTime(LocalDateTime.now());
        int updateCount = aiClientApiDao.updateById(queryResult);
        log.info("更新结果：{}", updateCount);

        // 5. 查询所有
        List<AiClientApi> allApis = aiClientApiDao.queryAll();
        log.info("查询所有记录数：{}", allApis.size());

        // 6. 删除测试
        int deleteCount = aiClientApiDao.deleteByApiId("test_api_001");
        log.info("删除结果：{}", deleteCount);
    }

    @Test
    public void testAiClientModelDao() {
        log.info("============ 测试 Model Config DAO ============");
        
        // 1. 插入测试数据
        AiClientModel model = AiClientModel.builder()
                .modelId("test_model_001")
                .apiId("test_api_001")
                .modelName("gpt-4-test")
                .modelType("openai")
                .status(1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        
        int insertCount = aiClientModelDao.insert(model);
        log.info("插入结果：{}", insertCount);

        // 2. 根据 ID 查询
        AiClientModel queryResult = aiClientModelDao.queryById(model.getId());
        log.info("根据 ID 查询结果：{}", queryResult);

        // 3. 根据 ModelID 查询
        AiClientModel queryByModelId = aiClientModelDao.queryByModelId("test_model_001");
        log.info("根据 ModelID 查询结果：{}", queryByModelId);

        // 4. 更新测试
        queryResult.setModelName("gpt-4-updated");
        queryResult.setUpdateTime(LocalDateTime.now());
        int updateCount = aiClientModelDao.updateById(queryResult);
        log.info("更新结果：{}", updateCount);

        // 5. 查询所有
        List<AiClientModel> allModels = aiClientModelDao.queryAll();
        log.info("查询所有记录数：{}", allModels.size());

        // 6. 根据模型类型查询
        List<AiClientModel> openaiModels = aiClientModelDao.queryByModelType("openai");
        log.info("OpenAI 模型记录数：{}", openaiModels.size());

        // 7. 删除测试
        int deleteCount = aiClientModelDao.deleteByModelId("test_model_001");
        log.info("删除结果：{}", deleteCount);
    }

    @Test
    public void testBatchOperations() {
        log.info("============ 测试批量操作 ============");
        
        // 创建多个关联数据
        AiClientApi api = AiClientApi.builder()
                .apiId("batch_api_001")
                .baseUrl("https://api.batch.com")
                .apiKey("batch_key")
                .completionsPath("v1/completions")
                .embeddingsPath("v1/embeddings")
                .status(1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        
        aiClientApiDao.insert(api);
        
        AiClientModel model = AiClientModel.builder()
                .modelId("batch_model_001")
                .apiId("batch_api_001")
                .modelName("batch-model")
                .modelType("openai")
                .status(1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        
        aiClientModelDao.insert(model);
        
        // 查询关联数据
        List<AiClientModel> modelsByApi = aiClientModelDao.queryByApiId("batch_api_001");
        log.info("根据 API ID 查询到的模型数：{}", modelsByApi.size());
        
        // 清理数据
        aiClientModelDao.deleteByModelId("batch_model_001");
        aiClientApiDao.deleteByApiId("batch_api_001");
        
        log.info("批量操作测试完成");
    }
}
