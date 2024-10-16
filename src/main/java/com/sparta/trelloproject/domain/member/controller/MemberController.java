package com.sparta.trelloproject.domain.member.controller;

import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.member.dto.MemberRequest;
import com.sparta.trelloproject.domain.member.dto.MemberSaveRequest;
import com.sparta.trelloproject.domain.member.dto.MemberSaveResponse;
import com.sparta.trelloproject.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workspaces/{workspaceId}")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberSaveResponse> saveMember(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody MemberSaveRequest request,
            @PathVariable Long workspaceId
    ) {
        return ResponseEntity.ok(
                memberService.saveMember(
                        authUser, request, workspaceId
                )
        );
    }

    @PatchMapping("/members/{memberId}")
    public ResponseEntity<MemberSaveResponse> updateMember(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody MemberRequest request,
            @PathVariable Long workspaceId,
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(
                memberService.updateMember(
                        authUser, request, workspaceId, memberId
                )
        );
    }
}
