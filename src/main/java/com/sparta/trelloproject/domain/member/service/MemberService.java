package com.sparta.trelloproject.domain.member.service;

import com.sparta.trelloproject.common.exception.CustomException;
import com.sparta.trelloproject.common.exception.ErrorCode;
import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.member.dto.request.MemberRequest;
import com.sparta.trelloproject.domain.member.dto.request.MemberSaveRequest;
import com.sparta.trelloproject.domain.member.dto.response.MemberSaveResponse;
import com.sparta.trelloproject.domain.member.entity.Member;
import com.sparta.trelloproject.domain.member.enums.Assign;
import com.sparta.trelloproject.domain.member.repository.MemberRepository;
import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.user.repository.UserRepository;
import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import com.sparta.trelloproject.domain.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

        User user = User.fromAuthUser(authUser);

        User newUser = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND, "추가할 사용자를 찾을 수 없습니다.")
        );

        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(
                () -> new CustomException(ErrorCode.WORKSPACE_NOT_FOUND)
        );

        if (newUser.getEmail().equals(authUser.getEmail())) {
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

        User user = User.fromAuthUser(authUser);

//        Member wsmember = memberRepository.findByUserId(authUser.getUserId()).orElseThrow(
//                () -> new CustomException(ErrorCode.USER_NOT_FOUND, "요청한 사용자를 찾을 수 없습니다.")
//        );

        Member member = memberRepository.findByUserId(memberId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND, "변경할 사용자를 찾을 수 없습니다.")
        );

        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(
                () -> new CustomException(ErrorCode.WORKSPACE_NOT_FOUND)
        );

        if (!request.getAssign().equals(Assign.BOARD.name()) &&
                !request.getAssign().equals(Assign.MANAGER.name()) &&
                !request.getAssign().equals(Assign.READ_ONLY.name())
        ) {
            throw new CustomException(ErrorCode.ROLE_NOT_FOUND, "존재하지 않는 권한입니다.");
        }

//        if (!wsmember.getAssign().equals(Assign.MANAGER)) {
//            throw new CustomException(ErrorCode.ROLE_ERROR, "워크스페이스 멤버만 접근할 수 있습니다.");
//        }

        Assign newAssign = Assign.valueOf(request.getAssign());
        if (member.getAssign() != Assign.MANAGER) {
            member.changeAssign(newAssign);
            memberRepository.save(member);
        }

        return new MemberSaveResponse(member);
    }
}
