package com.sparta.trelloproject.domain.board.dto.response;

import com.sparta.trelloproject.domain.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private String message; // 응답메세지
    private Long boardId;
    private String title;
    private String background;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // 보드 조회 시 응답
    public BoardResponse(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.background = board.getBackground();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.message = "보드 조회가 완료되었습니다.";
    }

    // 보드 생성 및 수정 시 응답
    public BoardResponse(Board board, String action) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.background = board.getBackground();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.message = action.equals("create") ? "보드가 생성되었습니다." : "보드가 수정되었습니다.";
    }
}
