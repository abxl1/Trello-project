package com.sparta.trelloproject.domain.member.service;

import com.sparta.trelloproject.common.exception.CustomException;
import com.sparta.trelloproject.common.exception.ErrorCode;
import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.member.dto.MemberRequest;
import com.sparta.trelloproject.domain.member.dto.MemberSaveRequest;
import com.sparta.trelloproject.domain.member.dto.MemberSaveResponse;
import com.sparta.trelloproject.domain.member.entity.Member;
import com.sparta.trelloproject.domain.member.repository.MemberRepository;
import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.user.repository.UserRepository;
import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import com.sparta.trelloproject.domain.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;

    @Transactional
    public MemberSaveResponse saveMember(
            AuthUser authUser,
            MemberSaveRequest request,
            Long workspaceId
    ) {

        User user = userRepository.findByEmail(authUser.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다.")
        );

        User newUser = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND, "추가할 사용자를 찾을 수 없습니다.")
        );

        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(
                () -> new CustomException(ErrorCode.WORKSPACE_NOT_FOUND)
        );

        if (user.getEmail().equals(authUser.getEmail())) {
            throw new CustomException(ErrorCode.SELF_REQUEST_FORBIDDEN, "본인을 초대할 수 없습니다.");
        }

        if (memberRepository.existsByUserId(newUser.getId())) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS, "이미 멤버가 존재합니다.");
        }

        Member member = memberRepository.save(new Member(newUser, workspace));
        return new MemberSaveResponse(member);
    }

    @Transactional
    public MemberSaveResponse updateMember(
            AuthUser authUser,
            MemberRequest request,
            Long workspaceId,
            Long memberId
    ) {

        User user = userRepository.findByEmail(authUser.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다.")
        );

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND, "변경할 사용자를 찾을 수 없습니다.")
        );

        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(
                () -> new CustomException(ErrorCode.WORKSPACE_NOT_FOUND)
        );

//        if (memberRepository.existsByAssign(request.getAssign())) {
//            throw new CustomException(ErrorCode.ROLE_NOT_FOUND, "존재하지 않는 권한입니다.");
//        }


        Member updatedMember = memberRepository.save(new Member(member, workspace));
        return new MemberSaveResponse(updatedMember);
    }
}
