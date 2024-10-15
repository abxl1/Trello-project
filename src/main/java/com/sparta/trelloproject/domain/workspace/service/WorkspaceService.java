package com.sparta.trelloproject.domain.workspace.service;

import com.sparta.trelloproject.common.exception.CustomException;
import com.sparta.trelloproject.common.exception.ErrorCode;
import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.user.request.UserRequest;
import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import com.sparta.trelloproject.domain.workspace.repository.WorkspaceRepository;
import com.sparta.trelloproject.domain.workspace.response.WorkspaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sparta.trelloproject.common.exception.ErrorCode.WORKSPACE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

  private final WorkspaceRepository workspaceRepository;

  public WorkspaceResponse getWorkspace(User user) {
    Workspace workspace = workspaceRepository.findByUserId(user.getId());
    if(workspace == null) {
      String errorMessage = WORKSPACE_NOT_FOUND.customMessage("유저 ID = " + user.getId());
      throw new CustomException(WORKSPACE_NOT_FOUND, errorMessage);
    }

    return new WorkspaceResponse(workspace);
  }

  public WorkspaceResponse createWorkspace(UserRequest userRequest) {
    Workspace workspace = new Workspace();


    return new WorkspaceResponse(workspace);
  }
}
