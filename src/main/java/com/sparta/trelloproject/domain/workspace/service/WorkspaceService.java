package com.sparta.trelloproject.domain.workspace.service;

import com.sparta.trelloproject.common.exception.CustomException;
import com.sparta.trelloproject.common.exception.ErrorCode;
import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.member.entity.Member;
import com.sparta.trelloproject.domain.member.repository.MemberRepository;
import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.user.repository.UserRepository;
import com.sparta.trelloproject.domain.user.request.UserGetRequest;
import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import com.sparta.trelloproject.domain.workspace.repository.WorkspaceRepository;
import com.sparta.trelloproject.domain.user.request.UserCreateRequest;
import com.sparta.trelloproject.domain.workspace.response.WorkspaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

  private final WorkspaceRepository workspaceRepository;
  private final UserRepository userRepository;
  private final MemberRepository memberRepository;

  // 워크스페이스 조회
  public WorkspaceResponse getWorkspace(AuthUser authUser, UserGetRequest userGetRequest) {
    // 멤버목록 조회
    List<Member> members = findMemberByUserId(authUser);

    // 유저가 조회하고자 하는 ID 워크스페이스 조회
    Workspace workspace = null;
    for(Member member : members) {
      workspace = findWorkspaceByMember(member);
      if(workspace.getId().equals(userGetRequest.getWorkspaceId())) {
        workspace = member.getWorkspace();
        break;
      }
    }
    if(workspace == null) {
      throw new CustomException(ErrorCode.WORKSPACE_NOT_FOUND);
    }

    return new WorkspaceResponse(workspace);
  }


  // 워크스페이스 생성
  public WorkspaceResponse createWorkspace(AuthUser authUser, UserCreateRequest userCreateRequest) {
    // 유저 조회
    User user = findUserById(authUser);

    // ADMIN 권한이 아니라면 예외발생
    if(!user.getUserRole().toString().equals("ROLE_ADMIN")){
      throw new CustomException(ErrorCode.PERMISSION_ERROR);
    }

    // 워크 스페이스 생성
    Workspace workspace = new Workspace(userCreateRequest.getTitle(), userCreateRequest.getExplaination(), user);

    // 워크스페이스 저장
    workspaceRepository.save(workspace);

    // 멤버 추가
    Member member = new Member(user, workspace);
    workspace.getMember().add(member);

    // 멤버 권한을 workspace로
    member.startAssign();
    memberRepository.save(member);

    // 역할

    return new WorkspaceResponse(workspace);
  }

  // 워크스페이스 삭제
  public void deleteWorkspace(AuthUser authUser, Long workspaceId) {
    // 유저 조회
    User user = findUserById(authUser);

    // ADMIN 권한이 아니라면 예외발생
    if(!user.getUserRole().toString().equals("ROLE_ADMIN")){
      throw new CustomException(ErrorCode.PERMISSION_ERROR);
    }

    workspaceRepository.deleteById(workspaceId);
  }


  ///////////////////////////////// 예외처리를 위한 매서드 //////////////////////////////////


  public User findUserById(AuthUser authUser) {
    return userRepository.findById(authUser.getUserId())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
  }

  private List<Member> findMemberByUserId(AuthUser authUser) {
    List<Member> members = memberRepository.findAllByUserId(authUser.getUserId());

    if(members.isEmpty()){
      throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
    }
    return members;
  }

  private List<Workspace> findWorkspacesByMember(Member member) {

    List<Workspace> workspaces = workspaceRepository.findAllByMember(member);

    if(workspaces.isEmpty()) {
      String errorMessage = ErrorCode.WORKSPACE_NOT_FOUND.customMessage("멤버 ID = " + member.getId());
      throw new CustomException(ErrorCode.WORKSPACE_NOT_FOUND, errorMessage);
    }

    return workspaces;
  }

  private Workspace findWorkspaceByMember(Member member) {
    return workspaceRepository.findByMember(member).orElseThrow(
        () -> new CustomException(ErrorCode.WORKSPACE_NOT_FOUND));
  }


}


