package com.sparta.trelloproject.domain.card.dto.response;

import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.comment.dto.response.CommentResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class CardDetailResponse {

    private String cardTitle;
    private String cardDescription;
    private List<CardActivityResponse> activities;
    private List<CommentResponse> comments;

    public CardDetailResponse(Card card, List<CardActivityResponse> activities, List<CommentResponse> comments){
        this.cardTitle = card.getTitle();
        this.cardDescription = card.getDescription();
        this.activities = activities;
        this.comments = comments;
    }
}
