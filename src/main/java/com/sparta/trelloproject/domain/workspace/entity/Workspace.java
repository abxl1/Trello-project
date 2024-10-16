package com.sparta.trelloproject.domain.workspace.entity;

import com.sparta.trelloproject.common.entity.Timestamped;
import com.sparta.trelloproject.domain.member.entity.Member;
import com.sparta.trelloproject.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
  private List<Member> member = new ArrayList<>();

  private String title;
  private String explaination;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;


  public Workspace(String title, String explaination, User user) {
    this.title = title;
    this.explaination = explaination;
    this.user = user;
  }


/*  public void addWorkspaceUser(WorkspaceUser member) {
    this.Member.add(member);
    member.addWorkspace(this);
  }

  public void removeWorkspaceUser(WorkspaceUser workspaceUser) {
    this.Member.remove(workspaceUser);
    workspaceUser.deleteWorkspace();
  }*/
}
