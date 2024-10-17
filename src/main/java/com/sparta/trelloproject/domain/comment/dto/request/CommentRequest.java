package com.sparta.trelloproject.domain.comment.dto.request;

import lombok.Getter;

@Getter
public class CommentRequest {
    private String text;
    private String emoji;

    public CommentRequest(String text, String emoji) {
        this.text = text;
        this.emoji = emoji;
    }
}
