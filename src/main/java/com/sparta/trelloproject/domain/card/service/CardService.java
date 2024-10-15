package com.sparta.trelloproject.domain.card.service;

import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.card.dto.request.CardSaveRequest;
import com.sparta.trelloproject.domain.card.dto.response.CardSaveResponse;
import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.card.repository.CardRepository;
import com.sparta.trelloproject.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardSaveResponse createCard(AuthUser authUser, Long boardId, Long listId, CardSaveRequest request) {

        User user = User.fromAuthUser(authUser);

        Card saveCard = cardRepository.save(new Card(request));

        return new CardSaveResponse(saveCard);

    }
}
