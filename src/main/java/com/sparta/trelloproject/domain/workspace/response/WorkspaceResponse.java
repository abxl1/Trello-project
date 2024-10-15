package com.sparta.trelloproject.domain.workspace.response;

import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import lombok.Getter;

@Getter
public class WorkspaceResponse {

  private Long id;
  private String title;

  public WorkspaceResponse(Workspace workspace) {
    this.id = workspace.getId();
    this.title = workspace.getTitle();
  }
}
