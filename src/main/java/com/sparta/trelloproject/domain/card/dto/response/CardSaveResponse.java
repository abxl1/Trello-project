package com.sparta.trelloproject.domain.card.dto.response;

import com.sparta.trelloproject.domain.card.entity.Card;
import lombok.Getter;

@Getter
public class CardSaveResponse {

    private final String title;
    private final String description;

    public CardSaveResponse(Card card){
        this.title = card.getTitle();
        this.description = card.getDescription();
    }

}
