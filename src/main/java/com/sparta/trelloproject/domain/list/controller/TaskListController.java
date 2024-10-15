package com.sparta.trelloproject.domain.list.controller;

import com.sparta.trelloproject.domain.list.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/{boardId}")
public class TaskListController {

    private final TaskListService taskListService;

//    @PostMapping("/lists")
//    public ResponseEntity<ListSaveResponse> saveList(@RequestBody ListSaveRequest listSaveRequest, @PathVariable("boardId") String boardId) {
//        return ResponseEntity.ok()
//
//    }
}
