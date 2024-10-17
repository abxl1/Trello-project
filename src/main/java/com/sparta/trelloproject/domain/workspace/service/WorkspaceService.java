package com.sparta.trelloproject.domain.workspace.service;

import com.sparta.trelloproject.common.exception.CustomException;
import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.member.entity.Member;
import com.sparta.trelloproject.domain.member.repository.MemberRepository;
import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.user.repository.UserRepository;
import com.sparta.trelloproject.domain.user.dto.request.UserGetRequest;
import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import com.sparta.trelloproject.domain.workspace.repository.WorkspaceRepository;
import com.sparta.trelloproject.domain.user.dto.request.UserCreateRequest;
import com.sparta.trelloproject.domain.workspace.response.WorkspaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sparta.trelloproject.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

  private final WorkspaceRepository workspaceRepository;
  private final UserRepository userRepository;
  private final MemberRepository memberRepository;

  // 워크스페이스 조회
  public WorkspaceResponse getWorkspace(AuthUser authUser, UserGetRequest userGetRequest) {
    // 멤버 조회
    Member member = findMemberByUserId(authUser);

    // 모든 워크스페이스 조회
    List<Workspace> workspaces = findWorkspacesByMember(member);

    // 유저가 조회하고자 하는 ID 워크스페이스 조회 (get 이라 -1)
    Long index = userGetRequest.getWorkspaceId() - 1;
    Workspace workspace = workspaces.get(index.intValue());

    if (index < 0 || index >= workspaces.size()) {
      throw new CustomException(WORKSPACE_NOT_FOUND);
    }

    return new WorkspaceResponse(workspace);
  }


  // 워크스페이스 생성
  public WorkspaceResponse createWorkspace(AuthUser authUser, UserCreateRequest userCreateRequest) {
    // 유저 조회
    User user = findUserById(authUser);

    // ADMIN 권한이 아니라면 예외발생
    if(!user.getUserRole().toString().equals("ROLE_ADMIN")){
      throw new CustomException(PERMISSION_ERROR);
    }

    // 워크 스페이스 생성
    Workspace workspace = new Workspace(userCreateRequest.getTitle(), userCreateRequest.getExplaination());

    // 워크스페이스 저장
    workspaceRepository.save(workspace);

    return new WorkspaceResponse(workspace);
  }

  // 워크스페이스 삭제
  public void deleteWorkspace(AuthUser authUser, Long workspaceId) {
    // 유저 조회
    User user = findUserById(authUser);

    // ADMIN 권한이 아니라면 예외발생
    if(!user.getUserRole().toString().equals("ROLE_ADMIN")){
      throw new CustomException(PERMISSION_ERROR);
    }

    workspaceRepository.deleteById(workspaceId);
  }


  ///////////////////////////////// 예외처리를 위한 매서드 //////////////////////////////////


  public User findUserById(AuthUser authUser) {
    return userRepository.findById(authUser.getUserId())
        .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
  }

  private Member findMemberByUserId(AuthUser authUser) {
    return memberRepository.findByUserId(authUser.getUserId())
        .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
  }

  private List<Workspace> findWorkspacesByMember(Member member) {

    List<Workspace> workspaces = workspaceRepository.findAllByMember(member);

    if(workspaces.isEmpty()) {
      String errorMessage = WORKSPACE_NOT_FOUND.customMessage("멤버 ID = " + member.getId());
      throw new CustomException(WORKSPACE_NOT_FOUND, errorMessage);
    }

    return workspaces;
  }



}


