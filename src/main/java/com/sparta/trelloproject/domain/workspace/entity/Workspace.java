package com.sparta.trelloproject.domain.workspace.entity;

import com.sparta.trelloproject.common.entity.Timestamped;
import com.sparta.trelloproject.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workspaces")
public class Workspace extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<WorkspaceUser> Member = new HashSet<>();

  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;


/*  public void addWorkspaceUser(WorkspaceUser member) {
    this.Member.add(member);
    member.addWorkspace(this);
  }

  public void removeWorkspaceUser(WorkspaceUser workspaceUser) {
    this.Member.remove(workspaceUser);
    workspaceUser.deleteWorkspace();
  }*/
}
