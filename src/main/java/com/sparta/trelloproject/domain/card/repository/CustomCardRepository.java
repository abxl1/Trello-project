package com.sparta.trelloproject.domain.card.repository;

import com.sparta.trelloproject.domain.card.dto.response.CardDetailResponse;

public interface CustomCardRepository {

    CardDetailResponse findByCardDetail(Long cardId);
}
