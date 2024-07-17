package com.example.claudedemo.service;

import com.example.claudedemo.model.ClaudeModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClaudeService {
    @Value("${claude.api.url}")
    private String url;
    @Value("${claude.api.key}")
    private String key;

    public List<String> getClaudeModels() {
        return Arrays.stream(ClaudeModel.values()).map(Enum::name).toList();
    }

    public String askClaude(ClaudeModel model, String question) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", key);

        String template = """
                {
                    "prompt": "${question}",
                    "model" : "${model}"
                }
                """;

        String body = template.replace("${model}", model.getValue())
                .replace("${question}", question);

        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
        log.info("response body :: {}", response.getBody());
        return response.getBody();
    }
}
