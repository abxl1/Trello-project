package com.sparta.trelloproject.domain.list.controller;

import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.list.dto.TaskListSaveRequest;
import com.sparta.trelloproject.domain.list.dto.TaskListSaveResponse;
import com.sparta.trelloproject.domain.list.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardId}")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;

    /**
     * @param request 리스트 생성 시 필요한 데이터
     * @param boardId 생성할 리스트의 대상 보드
     * @return HTTPStatus.CREATED
     */
    @PostMapping("/lists")
    public ResponseEntity<TaskListSaveResponse> saveList(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long boardId,
            @RequestBody TaskListSaveRequest request
    ) {
        TaskListSaveResponse response = taskListService.saveList(authUser, request, boardId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
//
//    /**
//     * @param boardId 생성할 리스트의 대상 보드
//     * @param page 조회할 페이지 수
//     * @param size 한 페이지당 데이터 수
//     * @return HTTPStatus.ok
//     */
//    @GetMapping("/lists")
//    public ResponseEntity<Page<TaskListResponse>> getLists(
//            @AuthenticationPrincipal AuthUser authUser,
//            @PathVariable("boardId") Long boardId,
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        return ResponseEntity.ok(taskListService.getLists(authUser, page, size, boardId));
//    }
//
//    /**
//     * @param boardId 생성할 리스트의 대상 보드
//     * @param listId 조회할 리스트의 아이디
//     * @return HTTPStatus.ok
//     */
//    @GetMapping("/lists/{listsId}")
//    public ResponseEntity<TaskListResponse> getList(
//            @AuthenticationPrincipal AuthUser authUser,
//            @PathVariable("boardId") Long boardId,
//            @PathVariable("boardId") Long listId
//    ) {
//        return ResponseEntity.ok(taskListService.getList(authUser, boardId, listId));
//    }
//
//    /**
//     * @param request 리스트 수정 시 필요한 데이터
//     * @param boardId 생성할 리스트의 대상 보드
//     * @param listsId 조회할 리스트의 아이디
//     * @return HTTPStatus.ok
//     */
//    @PatchMapping("/lists/{listsId}")
//    public ResponseEntity<TaskListResponse> updateList(
//            @AuthenticationPrincipal AuthUser authUser,
//            @RequestBody TaskListRequest request,
//            @PathVariable("boardId") Long boardId,
//            @PathVariable("boardId") Long listsId
//    ) {
//        return ResponseEntity.ok(taskListService.updateList(authUser, request, boardId, listsId));
//    }
//
//    /**
//     * @param boardId 생성할 리스트의 대상 보드
//     * @param listsId 조회할 리스트의 아이디
//     */
//    @DeleteMapping("/lists/{listsId}")
//    public void deleteList(
//            @AuthenticationPrincipal AuthUser authUser,
//            @PathVariable("boardId") Long boardId,
//            @PathVariable("boardId") Long listsId
//    ) {
//        taskListService.deleteList(authUser, boardId, listsId);
//    }
}
