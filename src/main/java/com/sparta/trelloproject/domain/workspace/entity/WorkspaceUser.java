//package com.sparta.trelloproject.domain.workspace.entity;
//
//import com.sparta.trelloproject.domain.user.entity.User;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "workspace_user")
//public class WorkspaceUser {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long id;
//
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "workspace_id")
//  private Workspace workspace;
//
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "user_id")
//  private User user;
//
//
//  /////////////////////////////////////////////////////////////
//
// /* public void addWorkspace(Workspace workspace) {
//    if (this.workspace == null) {
//      this.workspace = workspace;
//    }
//  }
//
//  public void deleteWorkspace() {
//    if (this.workspace != null) {
//      this.workspace = null;
//    }
//  }
//
//  public void addUser(User user) {
//    if (this.user == null) {
//      this.user = user;
//    }
//  }
//
//  public void deleteUser() {
//    if (this.user != null) {
//      this.user = null;
//    }
//  }*/
//}
