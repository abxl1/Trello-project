package com.sparta.trelloproject.domain.card.service;

import com.sparta.trelloproject.common.aop.CreateActivity;
import com.sparta.trelloproject.common.exception.CustomException;
import com.sparta.trelloproject.common.exception.ErrorCode;
import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.board.repository.BoardRepository;
import com.sparta.trelloproject.domain.card.dto.request.CardSaveRequest;
import com.sparta.trelloproject.domain.card.dto.request.CardUpdateRequest;
import com.sparta.trelloproject.domain.card.dto.response.CardDetailResponse;
import com.sparta.trelloproject.domain.card.dto.response.CardSaveResponse;
import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.card.repository.CardRepository;
import com.sparta.trelloproject.domain.list.entity.TaskList;
import com.sparta.trelloproject.domain.list.repository.TaskListRepository;
import com.sparta.trelloproject.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final TaskListRepository taskListRepository;
    private final BoardRepository boardRepository;

    @CreateActivity
    @Transactional
    public CardSaveResponse createCard(AuthUser authUser, Long boardId, Long listId, CardSaveRequest request) {

        User user = User.fromAuthUser(authUser);

        TaskList taskList = taskListRepository.findById(listId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        Long cardIndex = (long) taskList.getCards().size() + 1;

        Card saveCard = cardRepository.save(new Card(request, cardIndex));

        return new CardSaveResponse(saveCard);

    }

    @CreateActivity
    @Transactional
    public CardSaveResponse updateCard(AuthUser authUser, Long listId, Long cardId, CardUpdateRequest request) {

        User user = User.fromAuthUser(authUser);

        TaskList taskList = taskListRepository.findById(listId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        Long totalCardIndex = (long) taskList.getCards().size();

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CustomException(ErrorCode.CARD_NOT_FOUND));

        card.updateCard(request);

        if (request.getIndex() != null) {
            card.changeCardIndex(taskList, card, listId, request.getIndex(), totalCardIndex);
        }

        return new CardSaveResponse(card);
    }


    public CardDetailResponse searchCard(Long cardId) {

        return cardRepository.findByCardDetail(cardId);

    }

    @Transactional
    public void deleteCard(AuthUser authUser, Long boardId, Long listId, Long cardId) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CustomException(ErrorCode.CARD_NOT_FOUND));

        cardRepository.delete(card);
    }
}

