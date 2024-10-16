package com.sparta.trelloproject.domain.workspace.repository;

import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
  List<Workspace> findAllByUserId(long userid);
}
