package com.sparta.trelloproject.domain.user.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserGetRequest {

  private Long workspaceId;
}
