package com.sparta.trelloproject.domain.board.response;

import com.sparta.trelloproject.domain.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private Long boardId;
    private String title;
    private String background;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BoardResponse(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.background = board.getBackground();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}
