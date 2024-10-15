package com.sparta.trelloproject.domain.board.service;

import com.sparta.trelloproject.domain.board.entity.Board;
import com.sparta.trelloproject.domain.board.repository.BoardRepository;
import com.sparta.trelloproject.domain.board.request.BoardRequest;
import com.sparta.trelloproject.domain.board.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;

    // 보드 생성
    public BoardResponse createBoard(BoardRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        // 로그인하지 않은 멤버가 보드를 생성하려는 경우
        if(user == null) {
            throw new IllegalArgumentException("로그인하지 않은 멤버는 보드를 생성할 수 없습니다.");
        }

        // 제목이 비어 있는 경우
        if(request.getTitle() == null || request.getTitle().isEmpty()) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }

        // 워크스페이스 확인
        Workspace workspace = workspaceRepository.findById(request.getWorkspaceId)
                .orElseThrow(() -> new IllegalArgumentException("워크스페이스가 존재하지 않습니다."));

        // 읽기 전용 멤버 예외처리
        if(user.getRole() == UserRole.READ_ONLY) {
            throw new IllegalArgumentException("읽기 전용 권한입니다.");
        }

        Board board = new Board(request.getWorkspaceId(), userId, request.getTitle(), request.getBackground());
        boardRepository.save(board);
    }

    // 보드 수정
    public BoardResponse updateBoard(Long boardId, BoardRequest request, Long userId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("보드를 찾을 수 없습니다."));

        if(!board.getUserId().equals(userId)) {
            throw new IllegalArgumentException("보드 수정 권한이 없습니다.");
        }

        // 읽기 전용 멤버 예외처리
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        if (user.getRole() == UserRole.READ_ONLY) {
            throw new IllegalArgumentException("읽기 전용 권한입니다.");
        }

        board.update(request.getTitle(), request.getBackground());
        boardRepository.save(board);

        return  new BoardResponse(board);
    }

    // 보드 삭제 (연관된 리스트와 카드 삭제)
    public void deleteBoard(Long boardId,Long userId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("보드를 찾을 수 없습니다."));

        if(!board.getUserId().equals(userId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        // 읽기 전용 멤버 예외처리
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        if (user.getRole() == UserRole.READ_ONLY) {
            throw new IllegalStateException("읽기 전용 권한입니다.");
        }

        boardRepository.delete(board); // 연관된 리스트와 카드도 자동 삭제됨
    }
}
