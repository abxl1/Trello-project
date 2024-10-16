package com.sparta.trelloproject.domain.card.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.trelloproject.domain.card.dto.response.CardDetailResponse;
import com.sparta.trelloproject.domain.card.entity.QCard;
import com.sparta.trelloproject.domain.card.entity.QCardActivity;
import com.sparta.trelloproject.domain.comment.entity.QComment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class CardRepositoryImpl implements CustomCardRepository{

    @Autowired
    private  final JPAQueryFactory jpaQueryFactory;

    @Override
    public CardDetailResponse findByCardDetail(Long cardId) {

        QCard card = QCard.card;
        QCardActivity activity = QCardActivity.cardActivity;
        QComment comment = QComment.comment;

        CardDetailResponse result = jpaQueryFactory
                .select(Projections.constructor(CardDetailResponse.class,
                        card.title,
                        card.description,
                        activity.activity,
                        activity.timestamp
                        //comment.content
                ))
                .from(card)
                .leftJoin(card.cardActivities, activity)
                .leftJoin(card.comments, comment)
                .where(card.id.eq(cardId))
                .fetchOne();

        return result;
    }
}
