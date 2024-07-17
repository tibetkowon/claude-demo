package com.example.claudedemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClaudeModel {
    OPUS("claude-3-opus-20240229"),
    SONNET("claude-3-sonnet-20240229"),
    HAIKU("claude-3-haiku-20240307"),
    INSTANT("claude-instant-1.2"),
    ;
    private final String value;
}
