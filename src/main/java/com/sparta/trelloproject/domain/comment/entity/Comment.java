package com.sparta.trelloproject.domain.comment.entity;

import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.list.entity.TaskList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;
}
