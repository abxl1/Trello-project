package com.sparta.trelloproject.domain.card.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.trelloproject.domain.card.dto.request.CardSaveRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", nullable = false)
    private Long id;

    @Column(name = "card_title", nullable = false)
    private String title;

    @Column(name = "card_description", nullable = true)
    private String description;

    @Column(name = "card_index", nullable = false, unique = true)
    private Long index;

    @Column(name = "card_deadline", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadline;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "list_id")
//    private List list;

    @OneToMany(mappedBy = "card")
    private List<CardAssignee> cardAssignees = new ArrayList<>();

    @OneToMany(mappedBy = "card")
    private List<CardActivity> cardActivities = new ArrayList<>();

//    @OneToMany(mappedBy = "card")
//    private List<Comment> comments = new ArrayList<>();

    public Card(CardSaveRequest request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.deadline = request.getDeadline();
    }

}
