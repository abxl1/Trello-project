package com.sparta.trelloproject.domain.card.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.trelloproject.common.exception.CustomException;
import com.sparta.trelloproject.common.exception.ErrorCode;
import com.sparta.trelloproject.domain.card.dto.response.CardActivityResponse;
import com.sparta.trelloproject.domain.card.dto.response.CardDetailResponse;
import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.card.entity.CardActivity;
import com.sparta.trelloproject.domain.card.entity.QCard;
import com.sparta.trelloproject.domain.card.entity.QCardActivity;
import com.sparta.trelloproject.domain.comment.dto.response.CommentResponse;
import com.sparta.trelloproject.domain.comment.entity.Comment;
import com.sparta.trelloproject.domain.comment.entity.QComment;
import com.sparta.trelloproject.domain.user.entity.QUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.types.Projections.list;

@RequiredArgsConstructor
public class CardRepositoryImpl implements CustomCardRepository {

    @Autowired
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CardDetailResponse findByCardDetail(Long cardId) {

        QCard qCard = QCard.card;
        QCardActivity qActivity = QCardActivity.cardActivity;
        QComment qComment = QComment.comment;
        QUser qUser = QUser.user;

        List<Tuple> results = jpaQueryFactory
                .selectDistinct(qCard,
                        qCard.title, qCard.description,
                        qActivity.activity, qActivity.timestamp)
                .from(qCard)
                .join(qCard.cardActivities, qActivity).fetchJoin()
                .where(qCard.id.eq(cardId))
                .fetch();

        List<CardActivityResponse> activityResponses = results.stream()
                .map(tuple -> new CardActivityResponse(tuple.get(qActivity.activity), tuple.get(qActivity.timestamp)))
                .collect(Collectors.toList());

        List<Tuple> commentList = jpaQueryFactory
                .select(qComment, qUser.email)
                .from(qComment)
                .join(qComment.user, qUser).fetchJoin()
                .where(qComment.card.id.eq(cardId))
                .fetch();

        List<CommentResponse> commentResponses = commentList.stream()
                .map(tuple -> new CommentResponse(tuple.get(qComment).getCommentId(), tuple.get(qComment).getText(), tuple.get(qComment.user.email)))
                .collect(Collectors.toList());

        return new CardDetailResponse(results.get(0).get(qCard.title), results.get(0).get(qCard.description), activityResponses, commentResponses);
    }
}
