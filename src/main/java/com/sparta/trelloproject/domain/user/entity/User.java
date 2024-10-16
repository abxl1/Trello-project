package com.sparta.trelloproject.domain.user.entity;

import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.common.entity.Timestamped;
import com.sparta.trelloproject.domain.user.enums.UserRole;
import com.sparta.trelloproject.domain.workspace.entity.WorkspaceUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WorkspaceUser> workspaceUser = new HashSet<>();



/*    public void addWorkspaceUser(WorkspaceUser workspaceUser) {
        this.workspaceUser.add(workspaceUser);
        workspaceUser.addUser(this);
    }

    public void removeWorkspaceUser(WorkspaceUser workspaceUser) {
        this.workspaceUser.remove(workspaceUser);
        workspaceUser.deleteUser();
    }*/

    public User(String email, String password, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    private User(Long id, String email, UserRole userRole) {
        this.id = id;
        this.email = email;
        this.userRole = userRole;
    }

    public static User fromAuthUser(AuthUser authUser) {
        return new User(
                authUser.getUserId(),
                authUser.getEmail(),
                UserRole.of(authUser.getAuthorities().stream().findFirst().get().getAuthority())
        );
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void updateRole(UserRole userRole) {
        this.userRole = userRole;
    }
}