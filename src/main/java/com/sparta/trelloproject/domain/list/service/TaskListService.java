package com.sparta.trelloproject.domain.list.service;

import com.sparta.trelloproject.common.exception.CustomException;
import com.sparta.trelloproject.common.exception.ErrorCode;
import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.board.entity.Board;
import com.sparta.trelloproject.domain.board.repository.BoardRepository;
import com.sparta.trelloproject.domain.list.dto.request.TaskListRequest;
import com.sparta.trelloproject.domain.list.dto.request.TaskListSaveRequest;
import com.sparta.trelloproject.domain.list.dto.response.TaskListResponse;
import com.sparta.trelloproject.domain.list.dto.response.TaskListSaveResponse;
import com.sparta.trelloproject.domain.list.entity.TaskList;
import com.sparta.trelloproject.domain.list.repository.TaskListRepository;
import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.user.enums.UserRole;
import com.sparta.trelloproject.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 유저 찾기
    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    // 보드 찾기
    private Board getBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
    }

    // 유저 권한 검증
    private void validateUserRole(User user) {
        if(user.getUserRole() == UserRole.ROLE_USER) {
            throw new CustomException(ErrorCode.ROLE_ERROR);
        }
    }

    @Transactional
    public TaskListSaveResponse saveList(
            AuthUser authUser,
            TaskListSaveRequest request,
            Long boardId
    ) {
        User user = getUser(authUser.getUserId());
        validateUserRole(user); // 유저 권한 검증

        Board board = getBoard(boardId); // 보드 확인

        Long index = taskListRepository.countByBoardId(boardId);

        // TaskList 생성 후 저장
        TaskList taskList = new TaskList(request, board, index); // 수정된 생성자 사용
        TaskList savedTaskList = taskListRepository.save(taskList);

        return new TaskListSaveResponse(savedTaskList);
    }



//    @Transactional
//    public TaskListSaveResponse saveList(
//            AuthUser authUser,
//            TaskListSaveRequest request,
//            Long boardId
//    ) {
//
//        User user = userRepository.findByEmail(authUser.getEmail()).orElseThrow(
//                () -> new CustomException(ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다.")
//        );
//
//        if (authUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
//            throw new CustomException(ErrorCode.ROLE_ERROR, "읽기 전용 유저는 리스트를 생성할 수 없습니다.");
//        }
//
//        TaskList saveTaskList = taskListRepository.save(new TaskList(request));
//        return new TaskListSaveResponse(saveTaskList);
//    }

//    @Transactional(readOnly = true)
//    public TaskListResponse getList(
//            AuthUser authUser,
//            Long boardId,
//            Long listId
//    ) {
//        User user = getUser(authUser.getUserId());
//        validateUserRole(user); // 유저 권한 검증
//
//        Board board = getBoard(boardId); // 보드 확인
//        TaskList list = taskListRepository.findById(listId);
//
//        return null;
//    }

    @Transactional(readOnly = true)
    public Page<TaskListResponse> getLists(
            AuthUser authUser,
            int page,
            int size,
            Long boardId
    ) {

        return null;
    }

    @Transactional
    public TaskListResponse updateList(
            AuthUser authUser,
            TaskListRequest request,
            Long boardId,
            Long listId
    ) {
        return null;
    }

    @Transactional
    public void deleteList(
            AuthUser authUser,
            Long boardId,
            Long listId
    ) {

    }
}
