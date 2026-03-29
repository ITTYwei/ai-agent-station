package com.wei.ai.domain.agent.model.valobj;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AdvisorTypeEnumVO {
    CHAT_MEMORY("ChatMemory","记忆"),
    RAG_ANSWER("RagAnswer","访问文章提示词知识库");

    private String code;

    private String type;

}
