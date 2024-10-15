package com.sparta.trelloproject.domain.card.controller;

import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.card.dto.request.CardSaveRequest;
import com.sparta.trelloproject.domain.card.dto.response.CardSaveResponse;
import com.sparta.trelloproject.domain.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("boards/{boardId}/lists/{listId}")
public class CardController {

    private final CardService cardService;

    @PostMapping("/cards")
    public ResponseEntity<CardSaveResponse> createCard(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long boardId,
            @PathVariable Long listId,
            @RequestBody CardSaveRequest request){

        CardSaveResponse response = cardService.createCard(authUser, boardId, listId, request);
        return ResponseEntity.ok(response);

    }
//
//    @GetMapping("cards/{cardId}")
//    public ResponseEntity<>




}
