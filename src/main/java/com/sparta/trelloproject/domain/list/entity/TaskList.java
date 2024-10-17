package com.sparta.trelloproject.domain.list.entity;

import com.sparta.trelloproject.domain.board.entity.Board;
import com.sparta.trelloproject.domain.list.dto.request.TaskListSaveRequest;
import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.card.entity.Card;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "lists")
public class TaskList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "list_index", nullable = false)
    private Long index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public TaskList(TaskListSaveRequest request) {
        this.title = request.getTitle();
    }

    @OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    public TaskList(TaskListSaveRequest request, Board board, Long index) {
        this.title = request.getTitle();
        this.board = board; // 새로운 보드 객체 생성
        this.index = index;
    }
}
