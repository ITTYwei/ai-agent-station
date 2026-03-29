package com.wei.ai.domain.agent.model.entity;

import lombok.Data;

import java.util.List;

@Data
public class ArmoryCommandEntity {

    /**
     * 命令类型(client、model )
     */
    private String commandType;

    /**
     * 命令索引（clientId、modelId、apiId...）
     */
    private List<String> commandIdList;

}