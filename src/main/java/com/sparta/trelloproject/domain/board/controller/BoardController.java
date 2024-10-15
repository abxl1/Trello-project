package com.sparta.trelloproject.domain.board.controller;

import com.sparta.trelloproject.domain.board.request.BoardRequest;
import com.sparta.trelloproject.domain.board.response.BoardResponse;
import com.sparta.trelloproject.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 보드 생성
    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(
            @Auth AuthUser authUser,
            @RequestBody BoardRequest request) {
        BoardResponse response = boardService.createBoard(request, authUser.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 보드 수정

}
