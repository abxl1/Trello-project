package com.sparta.trelloproject.domain.card.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.trelloproject.domain.card.dto.response.CardActivityResponse;
import com.sparta.trelloproject.domain.card.dto.response.CardDetailResponse;
import com.sparta.trelloproject.domain.card.entity.QCard;
import com.sparta.trelloproject.domain.card.entity.QCardActivity;
import com.sparta.trelloproject.domain.comment.dto.response.CommentResponse;
import com.sparta.trelloproject.domain.comment.entity.QComment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor
public class CardRepositoryImpl implements CustomCardRepository{

    @Autowired
    private  final JPAQueryFactory jpaQueryFactory;

    @Override
    public CardDetailResponse findByCardDetail(Long cardId) {

        QCard card = QCard.card;
        QCardActivity activity = QCardActivity.cardActivity;
        QComment comment = QComment.comment;

        return jpaQueryFactory
                .select(Projections.constructor(CardDetailResponse.class,
                        card.title,
                        card.description,
                        Projections.list(
                                Projections.constructor(CardActivityResponse.class,
                                        activity.activity,
                                        activity.timestamp
                                )
                        ),
                        Projections.list(
                                Projections.constructor(CommentResponse.class,
                                        comment.user,
                                        comment.text,
                                        comment.createdAt
                                )
                        )
                ))
                .from(card)
                .leftJoin(card.cardActivities, activity).fetchJoin()
                .leftJoin(card.comments, comment).fetchJoin()
                .where(card.id.eq(cardId))
                .fetchOne();
    }
}
