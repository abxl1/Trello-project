package com.sparta.trelloproject.domain.card.controller;

import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.card.dto.request.CardSaveRequest;
import com.sparta.trelloproject.domain.card.dto.request.CardUpdateRequest;
import com.sparta.trelloproject.domain.card.dto.response.CardDetailResponse;
import com.sparta.trelloproject.domain.card.dto.response.CardSaveResponse;
import com.sparta.trelloproject.domain.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/{boardId}/lists/{listId}")
public class CardController {

    private final CardService cardService;

    @PostMapping("/cards")
    public ResponseEntity<CardSaveResponse> createCard(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable("boardId") Long boardId,
            @PathVariable("listId") Long listId,
            @RequestBody CardSaveRequest request) {

        CardSaveResponse response = cardService.createCard(authUser, boardId, listId, request);
        return ResponseEntity.ok(response);

    }

    @PatchMapping("/cards/{cardId}")
    public ResponseEntity<CardSaveResponse> updateCard(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long boardId,
            @PathVariable Long listId,
            @PathVariable Long cardId,
            @RequestBody CardUpdateRequest request
    ) {

        CardSaveResponse response = cardService.updateCard(authUser, listId, cardId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cards/{cardId}")
    public ResponseEntity<CardDetailResponse> searchCard(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long boardId,
            @PathVariable Long listId,
            @PathVariable Long cardId) {

        CardDetailResponse response = cardService.searchCard(cardId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cards/{cardId}")
    public ResponseEntity<String> deleteCard(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long boardId,
            @PathVariable Long listId,
            @PathVariable Long cardId) {

        cardService.deleteCard(authUser, boardId, listId, cardId);
        return ResponseEntity.ok("카드가 삭제됐습니다.");
    }

}
