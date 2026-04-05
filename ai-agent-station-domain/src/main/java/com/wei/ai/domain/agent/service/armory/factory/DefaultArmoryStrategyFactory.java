package com.wei.ai.domain.agent.service.armory.factory;

import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.wei.ai.domain.agent.model.entity.ArmoryCommandEntity;
import com.wei.ai.domain.agent.service.armory.RootNode;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DefaultArmoryStrategyFactory {

    @Resource
    private RootNode rootNode;


    public StrategyHandler<ArmoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext,String> armoryStrategyHandler() throws Exception {
        return rootNode;
    }
    /**
     * 数据上下文
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {

        /**
         *
         */
        private Map<String, Object> dataObjects = new HashMap<>();

        public <T> void setValue(String key, T value) {
            dataObjects.put(key, value);
        }

        public <T> T getValue(String key) {
            return (T) dataObjects.get(key);
        }
    }

}