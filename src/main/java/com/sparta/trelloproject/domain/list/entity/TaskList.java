package com.sparta.trelloproject.domain.list.entity;

import com.sparta.trelloproject.domain.board.entity.Board;
import com.sparta.trelloproject.domain.list.dto.TaskListSaveRequest;
import com.sparta.trelloproject.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "lists")
public class TaskList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "list_index", nullable = false, unique = true)
    private Long index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(mappedBy = "list")
//    private List<Card> cards = new ArrayList<>();

    public TaskList(TaskListSaveRequest request) {
        this.title = request.getTitle();
    }
}
