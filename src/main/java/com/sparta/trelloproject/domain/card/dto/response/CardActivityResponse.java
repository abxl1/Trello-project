package com.sparta.trelloproject.domain.card.dto.response;

import com.sparta.trelloproject.domain.card.entity.CardActivity;

import java.time.LocalDateTime;

public class CardActivityResponse {

    private String activity;

    private LocalDateTime timestamp;

    public CardActivityResponse(CardActivity cardActivity){
        this.activity = cardActivity.getActivity();
        this.timestamp = cardActivity.getTimestamp();
    }

}
