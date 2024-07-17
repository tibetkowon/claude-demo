package com.example.claudedemo;

import com.example.claudedemo.model.ClaudeModel;
import com.example.claudedemo.service.ClaudeService;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ClaudeTest {
    @Autowired
    private ClaudeService claudeService;

    @Test
    @DisplayName("모델 목록 확인")
    public void getClaudeModels() {
        List<String> models = claudeService.getClaudeModels();
        ListAssert<String> assertThat = assertThat(models);
        assertThat.withFailMessage("모델 목록 null").isNotNull();
        assertThat.withFailMessage("모델 목록 empty").isNotEmpty();
        assertThat.withFailMessage("모델 목록이 일치하지 않음 >> model size :: %s", models.size()).hasSize(ClaudeModel.values().length);
    }

    @Test
    @DisplayName("응답 여부 확인")
    public void getAnswer() {
        ClaudeModel model = ClaudeModel.OPUS;
        String question = "안녕?";

        String answer = claudeService.askClaude(model, question);
        assertThat(answer).withFailMessage("질문의 응답 받지 못함")
                .isNotEmpty();
    }
}
