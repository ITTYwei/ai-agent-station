package com.wei.ai.test.domain;


import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.wei.ai.domain.agent.model.entity.ArmoryCommandEntity;
import com.wei.ai.domain.agent.model.valobj.AiAgentEnumVO;
import com.wei.ai.domain.agent.service.armory.factory.DefaultArmoryStrategyFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@Slf4j
@SpringBootTest
public class AgentTest {

    @Resource
    private DefaultArmoryStrategyFactory defaultArmoryStrategyFactory;

    @Resource
    private ApplicationContext applicationContext;
    @Test
    public void test() throws Exception {
        StrategyHandler<ArmoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext, String> stringStrategyHandler = defaultArmoryStrategyFactory.armoryStrategyHandler();
        String apply = stringStrategyHandler.apply(ArmoryCommandEntity.builder()
                .commandType(AiAgentEnumVO.AI_CLIENT.getCode())
                .commandIdList(Arrays.asList("3001"))

                .build(), new DefaultArmoryStrategyFactory.DynamicContext());

        OpenAiApi bean = (OpenAiApi) applicationContext.getBean(AiAgentEnumVO.AI_CLIENT_API.getBeanName("1001"));

        log.info("测试结果{}",bean);
    }
}
