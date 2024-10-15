package com.sparta.trelloproject.domain.list.entity;

import com.sparta.trelloproject.domain.board.entity.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class TaskList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public TaskList(String title) {
        this.title = title;
    }
}
