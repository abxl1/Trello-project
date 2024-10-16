package com.sparta.trelloproject.domain.comment.entity;

import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String text;

    private String emoji;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;



    public Comment(User user, String text, String emoji, Card card) {
        this.user = user;
        this.text = text;
        this.emoji = emoji;
        this.card = card;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void update(String text, String emoji) {
        this.text = text;
        this.emoji = emoji;
        this.modifiedAt = LocalDateTime.now();
    }
}
