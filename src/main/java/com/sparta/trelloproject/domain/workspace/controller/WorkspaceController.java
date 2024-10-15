package com.sparta.trelloproject.domain.workspace.controller;

import com.sparta.trelloproject.config.JwtAuthenticationToken;
import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.user.request.UserRequest;
import com.sparta.trelloproject.domain.workspace.response.WorkspaceResponse;
import com.sparta.trelloproject.domain.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorkspaceController {

  private final WorkspaceService workspaceService;

  @GetMapping
  public ResponseEntity<WorkspaceResponse> getWorkspace(User user) {
    WorkspaceResponse response = workspaceService.getWorkspace(user);

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public void createWorkspace(@AuthenticationPrincipal JwtAuthenticationToken auth, @RequestBody UserRequest userRequest) {
    WorkspaceResponse response = workspaceService.createWorkspace(userRequest);

  }
}
