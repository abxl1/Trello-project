package com.sparta.trelloproject.domain.board.request;

import lombok.Getter;

@Getter
public class BoardRequest {
    private final Long workspaceId;
    private final String title;
    private final String background;

    public BoardRequest(Long workspaceId, String title, String background) {
        this.workspaceId = workspaceId;
        this.title = title;
        this.background = background;
    }
}
