package com.sparta.trelloproject.domain.list.controller;

import com.sparta.trelloproject.domain.list.dto.ListSaveRequest;
import com.sparta.trelloproject.domain.list.dto.ListSaveResponse;
import com.sparta.trelloproject.domain.list.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/{boardId}")
public class ListController {

    private final ListService listService;

//    @PostMapping("/lists")
//    public ResponseEntity<ListSaveResponse> saveList(@RequestBody ListSaveRequest listSaveRequest, @PathVariable("boardId") String boardId) {
//        return ResponseEntity.ok()
//
//    }
}
