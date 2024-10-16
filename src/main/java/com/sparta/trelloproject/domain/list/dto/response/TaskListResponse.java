package com.sparta.trelloproject.domain.list.dto.response;

import lombok.Getter;

@Getter
public class TaskListResponse {

    private final String message;

    public TaskListResponse(String message) {
        this.message = message;
    }
}
